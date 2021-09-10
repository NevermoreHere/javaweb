package com.example.feign;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}

