package com.zhuo.musiccommuity.Controller;


import com.alibaba.fastjson.JSON;

import com.zhuo.musiccommuity.Dao.UDao;
import com.zhuo.musiccommuity.Pojo.*;
import com.zhuo.musiccommuity.Service.*;
import com.zhuo.musiccommuity.UtilPackage.RedisUtil;
import io.shardingjdbc.core.jdbc.core.datasource.ShardingDataSource;
import io.shardingjdbc.core.keygen.DefaultKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.channels.FileChannel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {
    @Autowired
    UinfoService uif;
    @Autowired
    UserService us;
    @Autowired
    UploadSongService uss;
    @Autowired
    SongService ss;
    @Autowired
    MessageService ms;
    @Autowired
    ShardingDataSource shardingDataSource;

    @RequestMapping("/getLoveSong")
    @ResponseBody
    public List<Song> getLoveSong(@RequestParam(value = "uid",required = false)long uid){
        return uif.getLoveSong(uid);
    }
    @RequestMapping("/saveLoveSong")
    @ResponseBody
    public Status saveLoveSong(@RequestParam(value="uid",required = false)long uid,@RequestParam(value="sid")long sid){
        return uif.saveLoveSong(uid,sid);
    }
    @RequestMapping("/deleteLoveSong")
    @ResponseBody
    public long deleteLoveSong(){
        return 1;
    }
    @RequestMapping("/saveRecentMusic")
    @ResponseBody
    public void saveRecentMusic(@RequestParam(value="sid",required = false)long sid,@RequestParam(value="name",required = false) String name){
        ss.recordTOP((int)sid);
        uif.saveRecentMusic(sid,name);
    }
    @RequestMapping("/getRecentMusic")
    @ResponseBody
    public List<Song> getRecentMusic(@RequestParam(value="name",required = false)String name){
        return uif.getRecentMusic(name);
    }
    @RequestMapping("/getRecommendMusic")
    @ResponseBody
    public List<Song> getRecommendMusic(){
        return uif.getRecommendMusic();
    }
    @RequestMapping("/follow")
    @ResponseBody
    public Status follow(@RequestParam(value="userName",required = false)String userName,@RequestParam(value="uid",required = false)long uid){
        long otherUid = us.getUid(userName);
        return uif.updateLoveUser(uid,otherUid);
    }
    @RequestMapping("/followList")
    @ResponseBody
    public List<FollowListUser> followList(@RequestParam(value="uid",required = false)long uid){
        List<User> list = us.getUserList(uid);
        if(list!=null){
            return list.stream().map(user -> new FollowListUser(user.getName(),user.getUid())).collect(Collectors.toList());
        }
        else
            return new ArrayList<FollowListUser>();
    }
    @RequestMapping("/login")
    @ResponseBody
    public long checkLogin(@RequestBody User user){
        if(us.queryByName(user)){
            return us.getUid(user.getName());
        }
        else{
            return -1;
        }
    }
    @RequestMapping("/register")
    @ResponseBody
    public Status checkRegister(@RequestBody User user){
        if(us.queryByName(user)){
            return new Status(0,"用户已存在");
        }
        else {
            us.registe(user);
            return new Status(1,"注册成功");
        }
    }
    @RequestMapping("/uploadSong")
    @ResponseBody
    public String upload(@RequestParam(value ="songFile",required = false)MultipartFile songFile, @RequestParam(value ="coverFile",required = false)MultipartFile coverFile,@RequestParam("userName")String userName,@RequestParam("name")String name){
        try {
            String coverUrl = "uploadPic/moren.jpg";
            if(!coverFile.isEmpty()){
                FileInputStream fileInputStream = (FileInputStream) coverFile.getInputStream();
                File coverPic = new File("C:\\Users\\Administrator\\bishe\\src\\main\\resources\\static\\uploadPic\\"+userName+"uploadCover"+name+".jpg");
                FileOutputStream fileOutputStream = new FileOutputStream(coverPic);
                FileChannel in = fileInputStream.getChannel();
                FileChannel out = fileOutputStream.getChannel();
                in.transferTo(0,in.size(),out);
                coverUrl = "uploadPic/"+userName+"uploadCover"+name+".jpg";
            }

            FileInputStream fileInputStream1 = (FileInputStream) songFile.getInputStream();
            File song = new File("C:\\Users\\Administrator\\bishe\\src\\main\\resources\\static\\UploadSong\\"+userName+"uploadSong"+name+".mp3");
            FileOutputStream fileOutputStream1 = new FileOutputStream(song);
            FileChannel in1 = fileInputStream1.getChannel();
            FileChannel out1 = fileOutputStream1.getChannel();
            in1.transferTo(0,in1.size(),out1);

            UploadSong uploadSong = new UploadSong(name,coverUrl,"UploadSong/"+userName+"uploadSong"+name+".mp3",userName);
            uss.insert(uploadSong);
            Status s = new Status(1,"success");
            return JSON.toJSONString(s);
        } catch (IOException e) {
            e.printStackTrace();
            Status s1 = new Status(0,"failed");
            return JSON.toJSONString(s1);
        }
    }
    @RequestMapping("getUploadSong")
    @ResponseBody
    public List<UploadSong> getUploadSong(@RequestParam(value="userName",required = false) String userName){
        return uss.query(userName);
    }
    @RequestMapping("getIndexUploadSong")
    @ResponseBody
    public List<UploadSong> getIndexUploadSong(){
        return uss.getIndexUploadSong();
    }
    @RequestMapping("sendMessage")
    @ResponseBody
    public void sendMessage(@RequestBody Message message){
        message.initCreateTime();
        ms.sendMessage(message);
    }
    @RequestMapping("receiveMessage")
    @ResponseBody
    public List<Message> getMessage(@RequestParam(value="receiveUser",required = true)String receiveUser){
        return ms.getMessage(receiveUser);
    }
    @RequestMapping("newMessageNumber")
    @ResponseBody
    public int getCountOfNewMessage(@RequestParam(value="receiveUser",required = true)String receiveUser){
        return ms.getNewMessageNumber(receiveUser);
    }
    @RequestMapping("insertTest")
    @ResponseBody
    public String test(@RequestParam(value="id",required = true)long user_id,@RequestParam(value="name",required = true)String name,@RequestParam(value = "city")String city){
        UserTest userTest = new UserTest(user_id,name,city);
        us.insertTest(userTest);
        return "success";
    }
    @RequestMapping("insertManyTest")
    @ResponseBody
    public String manyTest()  {
        Connection con = null;
        try {
            con = shardingDataSource.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = null;
            String sql = "insert into user_info(user_id,name,city) values (?,?,?)";
            ps = con.prepareStatement(sql);
            List<UserTest> userTestList = new ArrayList<>();
            DefaultKeyGenerator defaultKeyGenerator = new DefaultKeyGenerator();
            for (int i = 0; i < 20; i++) {

                Long id = defaultKeyGenerator.generateKey().longValue();
                UserTest userTest = new UserTest(id,"a"+i,"b"+i);
                userTestList.add(userTest);
            }
            for (UserTest ut: userTestList
                    ) {
                ps.setLong(1,ut.getUser_id());
                ps.setString(2,ut.getName());
                ps.setString(3,ut.getCity());
                ps.addBatch();
            }
            ps.executeBatch();
            con.commit();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }



        System.out.println("success");
        return "success";
    }
}
