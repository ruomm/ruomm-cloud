package com.ruomm.springcloud.authserver.dao;
import com.ruomm.springcloud.authserver.entry.PaymentEntity;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface PaymentMapper extends Mapper<PaymentEntity>{
}
