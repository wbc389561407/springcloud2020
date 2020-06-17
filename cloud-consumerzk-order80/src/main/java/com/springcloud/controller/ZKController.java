package com.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ZKController {

    private static final String INVOEK_URL = "http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "consumer/payment/zk")
    public String paymentInfo(){
        String result = restTemplate.getForObject(INVOEK_URL+"/payment/zk",String.class);
        return result;
    }
}
