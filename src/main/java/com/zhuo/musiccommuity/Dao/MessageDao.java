package com.zhuo.musiccommuity.Dao;

import com.zhuo.musiccommuity.Pojo.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageDao {
    void insertMessage(Message message);
    List<Message> getUserMessage(String receiveUser);
}
