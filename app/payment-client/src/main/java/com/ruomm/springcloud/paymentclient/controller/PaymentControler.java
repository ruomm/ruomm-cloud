package com.ruomm.springcloud.paymentclient.controller;

import com.ruomm.springcloud.pojo.CommonResponse;
import com.ruomm.springcloud.pojo.request.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author 牛牛-研发部-www.ruomm.com
 * @version 1.0
 * @copyright 像衍科技-idr.ai
 * @create 2024/1/30 0:16
 */
@RestController
@RequestMapping("/consumer/payment")
@Slf4j
public class PaymentControler {

    //调用支付订单服务端的ip+端口号
    public static final  String PAYMENT_URL = "http://localhost:8001/api";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/create")
    public CommonResponse<Long> create(@RequestBody Payment dept){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",dept, CommonResponse.class);
    }
    @GetMapping("/get/{id}")
    public CommonResponse<Payment> queryById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResponse.class);
    }
}
