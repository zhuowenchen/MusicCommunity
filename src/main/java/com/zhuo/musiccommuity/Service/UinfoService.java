package com.zhuo.musiccommuity.Service;

import com.zhuo.musiccommuity.Pojo.Song;
import com.zhuo.musiccommuity.Pojo.Status;
import com.zhuo.musiccommuity.Pojo.User;
import com.zhuo.musiccommuity.Pojo.UserPageInfo;

import java.util.List;

public interface UinfoService {
    List<Song> getLoveSong(long uid);
    Status saveLoveSong(long uid, long loveSong);
    public void saveRecentMusic(long sid,String name);
    List<Song> getRecentMusic(String name);
    public List<Song> getRecommendMusic();
    Status updateLoveUser(long uid,long loveUser);
    UserPageInfo getUserPageMsg(long uid);
    User getUserPageMsgOfUser(long uid);
}
