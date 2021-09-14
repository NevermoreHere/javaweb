package com.example.feign;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FeignController{

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 返回远程调用的结果
     * @return
     */
    @RequestMapping("/get-service-detail")
    public String getUri(
            @RequestParam(value = "name", defaultValue = "") String name) {
        return JSON.toJSONString(discoveryClient.getInstances(name));
    }


    /**
     * 返回发现的所有服务
     * @return
     */
    @RequestMapping("/services")
    public String services() {
        return this.discoveryClient.getServices().toString();
    }
}