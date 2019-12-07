package com.zhuo.musiccommuity.Service;

import com.zhuo.musiccommuity.Pojo.FollowListUser;
import com.zhuo.musiccommuity.Pojo.User;
import com.zhuo.musiccommuity.Pojo.UserTest;

import java.util.List;

public interface UserService {
    public boolean queryByName(User user);
    public void registe(User user);
    public long getUid(String name);
    public List<User> getUserList(long uid);
    public List<FollowListUser> searchPeople(String searchPeopleName);
    public void insertTest(UserTest userTest);
    public void insertManyTest(List<UserTest> list);
}
