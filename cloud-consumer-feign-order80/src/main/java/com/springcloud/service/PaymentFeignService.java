package com.springcloud.service;

import com.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "cloud-provider-service")
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{code}")
    public CommonResult getPaymentByCode(@PathVariable("code") Integer code);

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout();

}
