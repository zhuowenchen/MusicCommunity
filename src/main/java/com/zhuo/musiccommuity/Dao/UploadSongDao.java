package com.zhuo.musiccommuity.Dao;

import com.zhuo.musiccommuity.Pojo.UploadSong;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UploadSongDao {
    List<UploadSong> tests();
    List<UploadSong> querys(List list);
    void insert(UploadSong uploadSong);
    List<UploadSong> query(String userName);
    int getLatestSongID();
}
