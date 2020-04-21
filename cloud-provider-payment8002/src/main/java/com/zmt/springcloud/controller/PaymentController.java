package com.zmt.springcloud.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.zmt.springcloud.entities.CommonResult;
import com.zmt.springcloud.entities.Payment;
import com.zmt.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Value;
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

    /**
     * test返回数据
     * @return
     */
    @GetMapping("get/list/{content}")
    public JSONObject getSolrShop(@PathVariable("content") String content){
        JSONObject js = new JSONObject();
        js.put("search_content",content);
        JSONArray ja=new JSONArray();
        JSONObject j1=new JSONObject();
        j1.put("id",1); //主键id
        j1.put("num_iid",1001); //商品id
        j1.put("title","海蓝之谜甄灿兰精华油30ml"); //商品id

        j1.put("pict_url","https://img.alicdn.com/imgextra/i1/3938714013/O1CN01AQXRJT1fW00M9fWy6_!!3938714013.jpg"); //商品主图
        j1.put("reserve_price",200); //商品一口价
        j1.put("zk_final_price",100); //商品折扣价
        j1.put("sort_val",2); //排序值
        j1.put("brand_id",3); //品牌id
        j1.put("brand_name","悦诗风吟"); //品牌名称

        ja.put(j1);

        JSONObject j2=new JSONObject();
        j2.put("id",2); //主键id
        j2.put("num_iid",1002); //商品id
        j2.put("title","姿美堂纤活酵素饮水果果蔬孝素原液非粉梅果冻清肠排益生菌宿便毒30ml"); //商品id

        j2.put("pict_url","https://img.alicdn.com/imgextra/i1/3938714013/O1CN01AQXRJT1fW00M9fWy6_!!3938714013.jpg"); //商品主图
        j2.put("reserve_price",300); //商品一口价
        j2.put("zk_final_price",50); //商品折扣价
        j2.put("sort_val",3); //排序值
        j2.put("brand_id",4); //品牌id
        j2.put("brand_name","姿美堂"); //品牌名称

        ja.put(j2);


        JSONObject j3=new JSONObject();
        j3.put("id",3); //主键id
        j3.put("num_iid",1003); //商品id
        j3.put("title","买2发10 姿美堂牡蛎片肽黄精牡蛎杞草杜蛎锌片覆盆子男性生蚝精华"); //商品id

        j3.put("pict_url","https://img.alicdn.com/imgextra/i1/3938714013/O1CN01AQXRJT1fW00M9fWy6_!!3938714013.jpg"); //商品主图
        j3.put("reserve_price",80); //商品一口价
        j3.put("zk_final_price",50); //商品折扣价
        j3.put("sort_val",4); //排序值
        j3.put("brand_id",5); //品牌id
        j3.put("brand_name","牡蛎"); //品牌名称

        ja.put(j3);

        js.put("search_list", ja);
        return js;
    }

}
