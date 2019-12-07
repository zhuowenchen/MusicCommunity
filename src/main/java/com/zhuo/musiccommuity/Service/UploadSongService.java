package com.zhuo.musiccommuity.Service;

import com.zhuo.musiccommuity.Pojo.UploadSong;

import java.util.List;

public interface UploadSongService {
    List<UploadSong> tests();
    void insert(UploadSong uploadSong);
    List<UploadSong> query(String userName);
    List<UploadSong> getIndexUploadSong();
}
