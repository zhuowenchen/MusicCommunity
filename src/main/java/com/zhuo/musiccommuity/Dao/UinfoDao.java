package com.zhuo.musiccommuity.Dao;

import com.zhuo.musiccommuity.Pojo.User;
import com.zhuo.musiccommuity.Pojo.UserPageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UinfoDao {

    String queryLoveSong(long uid);
    void saveLoveSong(@Param("uid")long uid,@Param("loveSong")String loveSong);
    String queryLoveUser(long uid);
    void updateLoveUser(@Param("uid")long uid,@Param("loveUser")String loveUser);
    UserPageInfo getUserPageMsg(long uid);
    User getUserPageMsgOfUser(long uid);
}
