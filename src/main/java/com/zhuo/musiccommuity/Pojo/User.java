package com.zhuo.musiccommuity.Pojo;

public class User {
    String name;
    String password;
    long uid;

    public String getLoveUser() {
        return loveUser;
    }

    public void setLoveUser(String loveUser) {
        this.loveUser = loveUser;
    }

    String loveUser;

    String loveSong;
    int identity;

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public String getLoveSong() {
        return loveSong;
    }

    public void setLoveSong(String loveSong) {
        this.loveSong = loveSong;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}
