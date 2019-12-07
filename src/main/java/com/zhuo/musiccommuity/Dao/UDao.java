package com.zhuo.musiccommuity.Dao;


import com.zhuo.musiccommuity.Pojo.User;
import com.zhuo.musiccommuity.Pojo.UserTest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UDao {

    User query(long uid);
    User queryByUser(User user);
    long getUid(String name);
    void insert(User user);
    List<User> querys(List list);
    List<User> searchPeople(String searchPeopleName);
    void insertTest(UserTest userTest);
    void insertManyTest(List list);
}
