package com.zhuo.musiccommuity.Pojo;

import javafx.scene.input.DataFormat;

import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Message {
    String sendUser;
    String receiveUser;
    String message;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH);
    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    String createTime;
    Message(){}
    Message(String sendUser,String receiveUser,String message){
        this.sendUser = sendUser;
        this.receiveUser = receiveUser;
        this.message = message;

    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public void initCreateTime(){
        dateFormat.setLenient(false);
        createTime = dateFormat.format(new Date());
    }

}
