package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //动态刷新
public class ConfigController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping(value = "/nacos/config")
    public String getConfigInfo(){
        return "configInfo:"+configInfo;
    }
}
