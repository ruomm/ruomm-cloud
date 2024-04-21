package com.ruomm.springcloud.paymentclient.dao;
import com.ruomm.springcloud.paymentclient.entry.PaymentEntity;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface PaymentMapper extends Mapper<PaymentEntity>{
}
