package com.zhuo.musiccommuity.Pojo;

public class Song {
    long sid;
    String song_name;
    String author;
    String url;
    public Song(){

    }
    public Song(String song_name,String author,String url){
        this.song_name = song_name;
        this.author = author;
        this.url = url;
    }
    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
