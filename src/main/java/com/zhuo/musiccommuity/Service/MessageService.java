package com.zhuo.musiccommuity.Service;


import com.zhuo.musiccommuity.Pojo.Message;

import java.util.List;

public interface MessageService {
    void sendMessage(Message message);
    List<Message> getMessage(String receiveUser);
    int getNewMessageNumber(String receiveUser);
}
