package com.springboot.servcice.impl;

import com.springboot.dao.PaymentDao;
import com.springboot.servcice.PaymentService;
import com.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceIpml implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public Payment getPaymentByCode(@Param("code") Integer code){
        return paymentDao.getPaymentByCode(code);
    }
}
