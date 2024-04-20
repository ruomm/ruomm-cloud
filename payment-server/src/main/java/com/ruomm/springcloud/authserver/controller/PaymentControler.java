package com.ruomm.springcloud.authserver.controller;

import com.ruomm.springcloud.authserver.service.PaymentService;
import com.ruomm.springcloud.authserver.utils.AppUtils;
import com.ruomm.springcloud.pojo.CommonResponse;
import com.ruomm.springcloud.pojo.request.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 牛牛-研发部-www.ruomm.com
 * @version 1.0
 * @copyright 像衍科技-idr.ai
 * @create 2024/1/30 0:16
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentControler {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public CommonResponse<Long> create(@RequestBody Payment dept){

        CommonResponse<Long> commonResponse = paymentService.create(dept);
        log.info("***************插入成功*******"+commonResponse.getData());
        if(AppUtils.success(commonResponse)){
            log.info("***************插入数据库成功*******"+commonResponse.getData());
        }else{
            log.info("***************插入数据库失败*******");
        }
        return commonResponse;
    }
    @GetMapping("/get/{id}")

    public CommonResponse<Payment> queryById(@PathVariable("id") Long id){
        CommonResponse commonResponse = paymentService.queryById(id);
        if(AppUtils.success(commonResponse)){
            log.info("***************查询成功*******");
        }else{
            log.info("***************查询失败*******");
        }
        return commonResponse;
    }
}
