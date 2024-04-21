package com.ruomm.springcloud.paymentclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;
@ConfigurationPropertiesScan
@SpringBootApplication
//@EnableJpaRepositories
@EnableScheduling
@MapperScan(basePackages = {"com.ruomm.springcloud.paymentclient.dao"})
public class WebPaymentClient {

    public static void main(String[] args) {
        SpringApplication.run(WebPaymentClient.class, args);
    }

}
