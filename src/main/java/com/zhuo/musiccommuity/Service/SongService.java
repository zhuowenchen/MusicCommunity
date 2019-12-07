package com.zhuo.musiccommuity.Service;

import com.zhuo.musiccommuity.Pojo.Song;

import java.util.List;

public interface SongService {
    List<Song> queryHotSong();
    List<Song> searchSong(String name);
    void insertManySongs(List<Song> songList);
    void recordTOP(int sid);
}
