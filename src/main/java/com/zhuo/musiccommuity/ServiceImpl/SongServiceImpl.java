package com.zhuo.musiccommuity.ServiceImpl;

import com.zhuo.musiccommuity.Dao.SongDao;
import com.zhuo.musiccommuity.Pojo.Song;
import com.zhuo.musiccommuity.Service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
public class SongServiceImpl implements SongService {
    @Autowired
    SongDao sd;
    @Override
    public List<Song> queryHotSong() {
        List<Integer> list = sd.selectHotSong();
        return sd.querys(list);
    }

    @Override
    public void insertManySongs(List<Song> songList) {
        sd.insertManySongs(songList);
    }

    @Override
    public List<Song> searchSong(String name) {
        return sd.searchSong(name);
    }

    @Transactional()
    @Override
    public void recordTOP(int sid) {
        sd.updateTOP(sid);
    }
}
