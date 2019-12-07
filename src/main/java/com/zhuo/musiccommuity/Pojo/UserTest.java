package com.zhuo.musiccommuity.Pojo;

public class UserTest {
    long user_id;
    String name;

    UserTest(){

    }
    public UserTest(long user_id,String name,String city){
        this.city = city;
        this.name = name;
        this.user_id = user_id;
    }
    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    String city;
}
