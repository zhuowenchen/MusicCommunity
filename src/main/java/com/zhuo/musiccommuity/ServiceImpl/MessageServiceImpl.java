package com.zhuo.musiccommuity.ServiceImpl;

import com.zhuo.musiccommuity.Dao.MessageDao;
import com.zhuo.musiccommuity.Pojo.Message;
import com.zhuo.musiccommuity.Service.MessageService;
import com.zhuo.musiccommuity.UtilPackage.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageDao messageDao;
    @Autowired
    RedisUtil redisUtil;
    @Override
    public void sendMessage(Message message) {
        redisUtil.incr("NumberOfMessage"+message.getReceiveUser(),1);
        messageDao.insertMessage(message);
    }

    @Override
    public List<Message> getMessage(String receiveUser) {
        redisUtil.set("NumberOfMessage"+receiveUser,0);
        return messageDao.getUserMessage(receiveUser);
    }

    @Override
    public int getNewMessageNumber(String receiveUser) {
        if(redisUtil.hasKey("NumberOfMessage"+receiveUser)){
            return (int)redisUtil.get("NumberOfMessage"+receiveUser);
        }
        else return 0;
    }
}
