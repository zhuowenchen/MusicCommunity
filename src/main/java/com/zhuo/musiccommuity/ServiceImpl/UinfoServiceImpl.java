package com.zhuo.musiccommuity.ServiceImpl;

import com.zhuo.musiccommuity.Dao.SongDao;
import com.zhuo.musiccommuity.Dao.UDao;
import com.zhuo.musiccommuity.Dao.UinfoDao;
import com.zhuo.musiccommuity.Pojo.Song;
import com.zhuo.musiccommuity.Pojo.Status;
import com.zhuo.musiccommuity.Pojo.User;
import com.zhuo.musiccommuity.Pojo.UserPageInfo;
import com.zhuo.musiccommuity.Service.UinfoService;
import com.zhuo.musiccommuity.UtilPackage.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UinfoServiceImpl implements UinfoService {
    @Autowired
    UinfoDao uinfoDao;
    @Autowired
    SongDao songDao;
    @Autowired
    RedisUtil ru;
    private void swap(int []array,int x,int y){
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
    @Override
    public List<Song> getLoveSong(long uid) {
        String list = uinfoDao.queryLoveSong(uid);
        if(list!=null&&!list.equals("")){
            String []l = list.split(",");
            List<String> as = Arrays.asList(l);
            return songDao.querys(as);
        }
        else return new ArrayList<Song>();
    }
    @Override
    public void saveRecentMusic(long sid, String name) {
        ru.lput(name,""+sid);
    }

    @Override
    public List<Song> getRecentMusic(String name) {
        List<String> songList = ru.lget(name);
        return  songDao.querys(songList);
    }
    public List<Song> getRecommendMusic(){
        int maxSid = songDao.getLatestSongID();
        //随机抽取N个数算法，降低冲突
        int []songArray = new int[maxSid+1];
        for (int i = 1; i < maxSid+1 ; i++) {
            songArray[i] = i;
        }
        Random r = new Random();
        List<String> songList = new ArrayList<>();
        for (int i = 0; i < 15 ; i++) {
            int temp = maxSid-i-r.nextInt(maxSid-i);
            songList.add(songArray[temp]+"");
            swap(songArray,temp,maxSid-i);
        }
        return songDao.querys(songList);
    }
    @Override
    public Status saveLoveSong(long uid, long loveSong) {
        String oldLoveSong = uinfoDao.queryLoveSong(uid);
        String sid = String.valueOf(loveSong);
        if(oldLoveSong!=null&&!oldLoveSong.equals("")){
            String []l = oldLoveSong.split(",");
            for (String a:l
                    ) {
                if(sid.equals(a)){
                    return new Status(0,"喜爱的音乐已存在");
                }
            }
            sid = oldLoveSong.concat(","+loveSong);
        }
        uinfoDao.saveLoveSong(uid,sid);
        return new Status(1,"收藏成功");
    }

    @Override
    public Status updateLoveUser(long uid, long loveUser) {
        String oldLoveUser = uinfoDao.queryLoveUser(uid);
        String loveUserId = String.valueOf(loveUser);
        if(oldLoveUser!=null&&!oldLoveUser.equals("")){
            String []l = oldLoveUser.split(",");
            for (String a:l
                    ) {
                if(loveUserId.equals(a)){
                    return new Status(0,"该用户已被关注");
                }
            }
            loveUserId = oldLoveUser.concat(","+loveUser);
        }
        uinfoDao.updateLoveUser(uid,loveUserId);
        return new Status(1,"关注成功");
    }

    @Override
    public UserPageInfo getUserPageMsg(long uid) {
        return uinfoDao.getUserPageMsg(uid);
    }

    @Override
    public User getUserPageMsgOfUser(long uid) {
        return uinfoDao.getUserPageMsgOfUser(uid);
    }
}
