package com.example.Video.service;

import com.alibaba.fastjson.JSONObject;
import com.example.Video.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2021-07-20
 */
public interface IVideoService extends IService<Video> {
    boolean videoUpload(MultipartFile[] files);

    File videoDownload(String path);
}
