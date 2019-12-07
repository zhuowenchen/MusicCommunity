package com.zhuo.musiccommuity.Pojo;

public class UserPageInfo {
    long uid;
    public String name;
    public String loveSong;
    public String loveUser;
    public String address;
    public String introduction;
    public String userCover;

    public String getUserCover() {
        return userCover;
    }

    public void setUserCover(String userCover) {
        this.userCover = userCover;
    }

    /*1为男，2为女*/
    public String sex;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoveSong() {
        return loveSong;
    }

    public void setLoveSong(String loveSong) {
        this.loveSong = loveSong;
    }

    public String getLoveUser() {
        return loveUser;
    }

    public void setLoveUser(String loveUser) {
        this.loveUser = loveUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
