package com.zhuo.musiccommuity.Pojo;

public class Status {
    long code;
    String message;
    public Status(){

    }
    public Status(long code,String message){
        this.code = code;
        this.message = message;
    }
    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
