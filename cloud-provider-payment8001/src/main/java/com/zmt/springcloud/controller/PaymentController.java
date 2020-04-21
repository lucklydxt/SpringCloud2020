package com.zmt.springcloud.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.zmt.springcloud.entities.CommonResult;
import com.zmt.springcloud.entities.Payment;
import com.zmt.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.List;

/**
 * @author dxt
 * @date 2020/4/8 10:48
 */
@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @PostMapping("insert")
    public CommonResult insert(@RequestBody Payment payment){
     int resultCode=paymentService.insert(payment);

     log.info("打印"+payment.toString());

     if(resultCode>0){
         return new CommonResult(200,"插入成功"+serverPort,resultCode);
     }else{
         return new CommonResult(400,"插入失败"+serverPort,null);
     }
    }

    @GetMapping("get/{id}")
    public CommonResult selectOne(@PathVariable("id") long id){

        Payment payment = paymentService.selectPaymentById(id);

        if(payment!=null){

          return new CommonResult(200,"查询成功"+serverPort,payment);
        }else{
          return new CommonResult(444,"查询失败"+serverPort,payment);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){

        List<String> services = discoveryClient.getServices();

        for (String service : services) {
            log.info("*********"+service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");

        for (ServiceInstance instance : instances) {
            log.info("*********"+instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

}
