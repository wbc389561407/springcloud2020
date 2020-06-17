package com.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.service.PaymentHystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
//@DefaultProperties(defaultFallback = "DefaultFallback")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;


    @HystrixCommand(fallbackMethod = "DefaultFallback")
    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        if(id<0){
            throw new RuntimeException("id不能为负数");
        }
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;

    };


    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
//    })
    public String paymentInfo_Timeout(@PathVariable("id")Integer id){
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        return result;
    };

//    public String paymentInfo_TimeoutHandler (@PathVariable("id")Integer id){
//        return "80端fallback"+id;
//    };
//
    public String DefaultFallback (@PathVariable("id")Integer id){
        return "80端DefaultFallback"+id;
    };

}
