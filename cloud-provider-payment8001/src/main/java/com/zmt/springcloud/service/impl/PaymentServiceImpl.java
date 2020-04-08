package com.zmt.springcloud.service.impl;

import com.zmt.springcloud.dao.PaymentDao;
import com.zmt.springcloud.entities.Payment;
import com.zmt.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * payment表服务实现类
 * @author dxt
 * @date 2020/4/8 10:16
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int insert(Payment payment) {
        return paymentDao.insert(payment);
    }

    @Override
    public Payment selectPaymentById(long id) {
        return paymentDao.selectPaymentById(id);
    }
}
