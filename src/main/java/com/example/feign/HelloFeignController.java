package com.example.feign;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Api(value = "外部服务")
@RequestMapping("/feign")
public class HelloFeignController {
    @RequestMapping("/services")
    public String hello() {
        System.out.println("services");
        return client.getServices();
    }

    @GetMapping("/test/services")
    public String services() {
        return serviceClient.getServices();
    }

    @FeignClient(value = "java-k8s-svc")
    interface HelloClient {
        @RequestMapping(value = "/services", method = GET)
        String getServices();
    }

    @FeignClient(value = "java-example-svc")
    interface ServiceClient {
        @RequestMapping(value = "/services", method = GET)
        String getServices();
    }

    @Autowired
    HelloClient client;

    @Autowired
    ServiceClient serviceClient;

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
    @RequestMapping("/services/test")
    public String servicesT() {
        return this.discoveryClient.getServices().toString();
    }
}

