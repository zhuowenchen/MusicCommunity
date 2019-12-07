package com.zhuo.musiccommuity.ServiceImpl;

import com.zhuo.musiccommuity.Dao.UploadSongDao;
import com.zhuo.musiccommuity.Pojo.UploadSong;
import com.zhuo.musiccommuity.Service.UploadSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UploadSongServiceImpl implements UploadSongService {
    @Autowired
    UploadSongDao usd;
    private void swap(int []array,int x,int y){
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
    @Override
    public List<UploadSong> tests() {
        return usd.tests();
    }

    @Override
    public void insert(UploadSong uploadSong) {
        usd.insert(uploadSong);
    }

    @Override
    public List<UploadSong> query(String userName) {
        return usd.query(userName);
    }

    @Override
    public List<UploadSong> getIndexUploadSong() {
        int maxId = usd.getLatestSongID();
        int []songArray = new int[maxId+1];
        for (int i = 1; i < maxId+1 ; i++) {
            songArray[i] = i;
        }
        Random r = new Random();
        List<Integer> songList = new ArrayList<>();
        for (int i = 0; i < 6 ; i++) {
            int temp = maxId-i-r.nextInt(maxId-i);
            songList.add(songArray[temp]);
            swap(songArray,temp,maxId-i);
        }
        return usd.querys(songList);
    }
}
