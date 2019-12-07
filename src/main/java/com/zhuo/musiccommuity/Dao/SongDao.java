package com.zhuo.musiccommuity.Dao;

import com.zhuo.musiccommuity.Pojo.Song;
import com.zhuo.musiccommuity.Pojo.TimesOfPlay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SongDao {
    List<Song> query();
    List<Song> querys(List list);
    List<Song> searchSong(String name);
    int getLatestSongID();
    void insertSong(Song song);
    void insertManySongs(List list);
    void insertTOP(int sid);
    void updateTOP(int sid);
    TimesOfPlay selectTOP(int sid);
    List<Integer> selectHotSong();
}
