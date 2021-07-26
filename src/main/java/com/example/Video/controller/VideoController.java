package com.example.Video.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/Video/video")
public class VideoController {
    @GetMapping("/list")
    @ApiOperation(value = "列表")
    public void getVideoList(){
        System.out.println("测试");
    }

}
