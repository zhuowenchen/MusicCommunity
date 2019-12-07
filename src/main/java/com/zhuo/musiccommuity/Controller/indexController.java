package com.zhuo.musiccommuity.Controller;


import com.zhuo.musiccommuity.Dao.UinfoDao;
import com.zhuo.musiccommuity.Pojo.*;
import com.zhuo.musiccommuity.Service.SongService;
import com.zhuo.musiccommuity.Service.UinfoService;
import com.zhuo.musiccommuity.Service.UploadSongService;
import com.zhuo.musiccommuity.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class indexController {

    @Autowired
    SongService songService;
    @Autowired
    UserService userService;
    @Autowired
    UinfoService uinfoService;
    @Autowired
    UploadSongService uploadSongService;
    @RequestMapping("/index")
    public String index(Model model){
        return "index";
    }
    @RequestMapping("/user")
    public String others(Model model){
        return "user";
    }
    @RequestMapping("/Recent")
    public String recent(Model model){
        return "RecentMusic";
    }
    @RequestMapping("/Favorite")
    public String favorite(Model model){
        return "FavoriteMusic";
    }
    @RequestMapping("/worksMusic")
    public String worksMusic(Model model){
        return "worksMusic";
    }
    @RequestMapping("/recommendMusic")
    public String recommendMusic(Model model){
        return "recommendMusic";
    }
    @RequestMapping("/musicTable")
    @ResponseBody
    public List<Song> getSong(){
        return songService.queryHotSong();
    }

    @RequestMapping("/search")
    @ResponseBody
    public List<Song> search(@RequestParam(value="name",required = false)String name){
        return songService.searchSong(name);
    }
    @RequestMapping("/searchPeople")
    @ResponseBody
    public List<FollowListUser> searchPeople(@RequestParam(value = "searchPeopleName",required = false)String searchPeopleName){
        return userService.searchPeople(searchPeopleName);
    }
    @RequestMapping("/otherFavorite")
    public String  otherFavorite(Model model, @RequestParam(value = "uid",required = false)long uid){
        model.addAttribute("otherUid",uid);
        return "otherFavoriteMusic";
    }
    @RequestMapping("/userPage")
    public String userPage(Model model,@RequestParam(value = "uid")long uid){
        model.addAttribute("uid",uid);
        UserPageInfo upi = uinfoService.getUserPageMsg(uid);
        if( upi == null){
            upi = new UserPageInfo();
            User temp = uinfoService.getUserPageMsgOfUser(uid);
            upi.setAddress("地址未明");
            upi.setSex("男");
            upi.setIntroduction("主人还没写任何东西");
            upi.setUid(uid);
            upi.setLoveUser(temp.getLoveUser());
            upi.setLoveSong(temp.getLoveSong());
            upi.setName(temp.getName());
        }
        model.addAttribute("UserPageInfo",upi);
        List<Song> songList = uinfoService.getLoveSong(uid);
        model.addAttribute("listOfSong",songList);
        List<UploadSong> uploadSongList = uploadSongService.query(upi.getName());
        model.addAttribute("ListOfUploadSong",uploadSongList);
        List<User> list = userService.getUserList(uid);
        List<FollowListUser> followListUserList;
        if(list!=null){
            followListUserList = list.stream().map(user -> new FollowListUser(user.getName(),user.getUid())).collect(Collectors.toList());
        }
        else{
            followListUserList = new ArrayList<>();
        }
        model.addAttribute("ListOfFollowUser",followListUserList);
        return "userPage";
    }
    @RequestMapping(value = "/Download", method = RequestMethod.GET)
    public void Download(@RequestParam(value="name")String fileName, HttpServletResponse res) {
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File("d://os//"
                    + fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }
}
