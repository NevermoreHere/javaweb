package com.example.Video.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.Utils.CommonResult;
import com.example.Video.service.IVideoService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2021-07-20
 */
@RestController
@Api(value = "视频信息")
@RequestMapping("/video")
public class VideoController {
    @Autowired
    IVideoService service;

    @GetMapping("/list")
    @ApiOperation(value = "列表")
    public void getVideoList(){
        System.out.println("测试");
    }

    @PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "上传")
    public CommonResult uploadFile(@RequestParam("files") MultipartFile[] files){
        service.videoUpload(files);
        return new CommonResult(new JSONObject());
    }

    @PostMapping(value = "/download", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "上传")
    public CommonResult downloadFile(@RequestParam("files") MultipartFile[] files){
        service.videoUpload(files);
        return new CommonResult(new JSONObject());
    }
}
