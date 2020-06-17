package com.springcloud.service;

import org.springframework.stereotype.Component;

//测试目标服务器宕机 可以 异常进入不了
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService  paymentInfo_OK ID:"+id;
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "PaymentFallbackService  paymentInfo_Timeout ID:"+id;
    }


}
