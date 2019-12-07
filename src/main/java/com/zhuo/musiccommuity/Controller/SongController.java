package com.zhuo.musiccommuity.Controller;

import com.zhuo.musiccommuity.Pojo.Song;
import com.zhuo.musiccommuity.Pojo.UploadSong;
import com.zhuo.musiccommuity.Service.SongService;
import com.zhuo.musiccommuity.Service.UploadSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
public class SongController {
    @Autowired
    SongService ss;
    @Autowired
    UploadSongService uss;
    @RequestMapping("/testUss")
    @ResponseBody
    public List<UploadSong> tests(){
        return uss.tests();
    }
    @RequestMapping("/insertSong")
    @ResponseBody
    public void insertSong() {
        System.out.println(System.currentTimeMillis());
        File file = new File("C:\\Users\\Administrator\\bishe\\src\\main\\resources\\static\\Song");
        List<Song> need = new ArrayList<>();
        if(file.exists()){
            LinkedList<File> list = new LinkedList<>();

            File []files = file.listFiles();
            for (File f:files
                 ) {
                String url = f.getPath().substring(55);
                String name;
                String author;
                if(!url.contains("-")){
                    name = url.substring(url.lastIndexOf("\\")+1,url.lastIndexOf('.'));
                    author = "unknown";
                }
                else{
                    name = url.substring(url.lastIndexOf("-")+2,url.lastIndexOf("."));
                    author = url.substring(url.lastIndexOf("\\")+1,url.indexOf("-")-1);
                }
                Song temp = new Song(name,author,url);
                need.add(temp);
            }
        }
        ss.insertManySongs(need);
    }

}
