package com.zmt.springcloud.controller;

import com.zmt.springcloud.entities.CommonResult;
import com.zmt.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author dxt
 * @date 2020/4/8 15:27
 */
@RestController
@RequestMapping("consumer")
public class OrderController {

    //public static final String PAYMENT_URL="http://localhost:8001";
    public static final String PAYMENT_URL="http://CLOUD-PROVIDER-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("payment/insert")
    public CommonResult<Payment> insert(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/insert",payment,CommonResult.class);
    }

    @GetMapping("payment/get/{id}")
    public CommonResult<Payment> selectPaymentById(@PathVariable("id") Long id){

        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

}
