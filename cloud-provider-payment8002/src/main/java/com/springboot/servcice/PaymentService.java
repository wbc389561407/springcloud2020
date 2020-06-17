package com.springboot.servcice;

import com.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentByCode(@Param("code") Integer code);
}
