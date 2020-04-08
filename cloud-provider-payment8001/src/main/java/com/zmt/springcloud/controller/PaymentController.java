package com.zmt.springcloud.controller;

import com.zmt.springcloud.entities.CommonResult;
import com.zmt.springcloud.entities.Payment;
import com.zmt.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;

/**
 * @author dxt
 * @date 2020/4/8 10:48
 */
@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @PostMapping("insert")
    public CommonResult insert(@RequestBody Payment payment){
     int resultCode=paymentService.insert(payment);

     log.info("打印"+payment.toString());

     if(resultCode>0){
         return new CommonResult(200,"插入成功",resultCode);
     }else{
         return new CommonResult(400,"插入失败",null);
     }
    }

    @GetMapping("get/{id}")
    public CommonResult selectOne(@PathVariable("id") long id){

        Payment payment = paymentService.selectPaymentById(id);

        return new CommonResult(200,"查询成功",payment);
    }

}
