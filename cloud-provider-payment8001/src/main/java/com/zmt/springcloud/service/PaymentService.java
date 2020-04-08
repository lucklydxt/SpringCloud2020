package com.zmt.springcloud.service;

import com.zmt.springcloud.entities.Payment;

/**
 * payment表服务接口
 * @author dxt
 * @date 2020/4/8 10:11
 */
public interface PaymentService {

    int insert(Payment payment);

    Payment selectPaymentById(long id);
}
