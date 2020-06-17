package com.springboot.controller;

import com.springboot.servcice.PaymentService;
import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class PaymentController
{
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        System.out.println("******插入结果："+result);
        if(result>0){
            return new CommonResult(200,"插入数据库成功"+serverPort,result);

        }else {
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{code}")
    public CommonResult getPaymentByCode(@PathVariable("code") Integer code){
        System.out.println("***************");
        Payment paymentByCode = paymentService.getPaymentByCode(code);
        System.out.println("******插入结果："+paymentByCode);
        if(paymentByCode != null){
            return new CommonResult(200,"查询数据库成功"+serverPort,paymentByCode);

        }else {
            return new CommonResult(444,"查询数据库失败"+code,null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object getDiscoveryClient() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                System.out.println(instance.getServiceId()+" " + instance.getHost()+" " +instance.getPort()+" " +instance.getUri());
            }
        }
        return discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLb(){
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping(value = "/payment/zipkin")
    public String paymentZipkin(){
        return serverPort;
    }
}
