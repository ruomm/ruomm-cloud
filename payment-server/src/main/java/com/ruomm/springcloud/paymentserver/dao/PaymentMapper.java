package com.ruomm.springcloud.paymentserver.dao;
import com.ruomm.springcloud.paymentserver.entry.PaymentEntity;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface PaymentMapper extends Mapper<PaymentEntity>{
}
