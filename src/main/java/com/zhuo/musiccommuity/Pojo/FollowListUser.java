package com.zhuo.musiccommuity.Pojo;

public class FollowListUser {
    String name;
    long uid;
    public FollowListUser(String name,long uid){
        this.name = name;
        this.uid = uid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}
