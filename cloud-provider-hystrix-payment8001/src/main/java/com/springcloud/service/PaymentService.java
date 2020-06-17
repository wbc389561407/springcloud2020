package com.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id){
        return "线程池" + Thread.currentThread().getName()+ "paymentInfo_OK_ID:"+id +"_OK";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_Timeout(Integer id){
        int time = id;
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池" + Thread.currentThread().getName()+ "paymentInfo_Timeout_ID:"+id +"_耗时"+time+"秒钟";
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池" + Thread.currentThread().getName()+ "8001服务繁忙或者运行报错:"+id;
    }

    //========服务熔断


    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties ={
            @HystrixProperty(name= "circuitBreaker.enabled",value = "true"),//是否使用断路器
            @HystrixProperty(name= "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name= "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口
            @HystrixProperty(name= "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率到后跳闸
    })
    public String paymentCircuitBreaker(Integer id){
        if(id<0){
            throw new RuntimeException("id不能小于0");
        }
        return Thread.currentThread().getName()+" 调用成功，流水号："+UUID.randomUUID().toString();
    }

    public String paymentCircuitBreaker_fallback(Integer id){
        return "id 不能为负数，请重新尝试，id:"+id;
    }
}
