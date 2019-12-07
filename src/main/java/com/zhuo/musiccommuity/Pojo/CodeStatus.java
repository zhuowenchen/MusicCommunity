package com.zhuo.musiccommuity.Pojo;

public enum CodeStatus {
    SUCCESS("success",1),FAILURE("fail",0);
    private String message;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private CodeStatus(String message, int code){
        this.message = message;
        this.code = code;
    }
}
