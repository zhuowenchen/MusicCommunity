package com.zhuo.musiccommuity.Pojo;

import java.util.Date;

public class UploadSong {
    long id;
    String name;
    String coverUrl;
    String songUrl;
    String userName;
    Date createTime;
    public UploadSong(){

    }
    public UploadSong(String name,String coverUrl,String songUrl,String userName){
        this.name = name;
        this.coverUrl = coverUrl;
        this.songUrl = songUrl;
        this.userName = userName;
        this.createTime = new Date();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}
