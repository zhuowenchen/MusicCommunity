package com.zhuo.musiccommuity.ServiceImpl;

import com.zhuo.musiccommuity.Dao.UDao;
import com.zhuo.musiccommuity.Pojo.User;
import com.zhuo.musiccommuity.Service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    UDao userDao;

    @Override
    public User query(long uid) {
        return userDao.query(uid);
    }}
