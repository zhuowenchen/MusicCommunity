package com.zhuo.musiccommuity.ServiceImpl;

import com.zhuo.musiccommuity.Dao.UDao;
import com.zhuo.musiccommuity.Dao.UinfoDao;
import com.zhuo.musiccommuity.Pojo.FollowListUser;
import com.zhuo.musiccommuity.Pojo.User;
import com.zhuo.musiccommuity.Pojo.UserTest;
import com.zhuo.musiccommuity.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    UDao uDao;
    @Autowired
    UinfoDao uinfoDao;

    public boolean queryByName(User user){
        User u = uDao.queryByUser(user);
        if(u!=null&&u.getPassword().equals(user.getPassword())){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void registe(User user) {
        uDao.insert(user);

    }

    @Override
    public long getUid(String name) {
        return uDao.getUid(name);
    }

    @Override
    public List<User> getUserList(long uid) {
        String loveUser = uinfoDao.queryLoveUser(uid);
        List<String> list ;
        if(loveUser!=null&&!loveUser.equals("")){
            String []user = loveUser.split(",");
            list = Arrays.asList(user);
            return uDao.querys(list);
        }
        else return null;
    }

    @Override
    public List<FollowListUser> searchPeople(String searchPeopleName) {
        List<User> user = uDao.searchPeople(searchPeopleName);
        List<FollowListUser> followListUser;
        if(user!=null){
            followListUser  = user.stream().map(user1 -> new FollowListUser(user1.getName(),user1.getUid())).collect(Collectors.toList());
        }
        else {
            followListUser = new ArrayList<>();
        }
        return followListUser;
    }

    @Override
    public void insertTest(UserTest userTest) {
        uDao.insertTest(userTest);
    }
    public void insertManyTest(List<UserTest> list){
        uDao.insertManyTest(list);
    }
}
