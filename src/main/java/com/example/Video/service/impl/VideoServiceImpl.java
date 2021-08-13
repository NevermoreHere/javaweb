package com.example.Video.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.Video.entity.Video;
import com.example.Video.mapper.VideoMapper;
import com.example.Video.service.IVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-07-20
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {
    private static final String FILE_PATH = "/Users/xulide/Documents/target/";

    public boolean videoUpload(MultipartFile[] files){

        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String path = FILE_PATH + fileName;
            File dest = new File(path);
            try {
                file.transferTo(dest);
            }catch (IOException | IllegalStateException e){
                System.out.println(e);
                return false;
            }
        }

        return true;
    }

    public File videoDownload(String fileName){
        String path = FILE_PATH + fileName;
        File file = new File(path);

        if (file.exists()){
            return file;
        }
        return null;
    }
}
