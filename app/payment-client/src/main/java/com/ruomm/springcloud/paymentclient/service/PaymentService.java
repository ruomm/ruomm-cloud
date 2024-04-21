package com.ruomm.springcloud.paymentclient.service;

import com.ruomm.springcloud.exception.WebAppException;
import com.ruomm.springcloud.paymentclient.config.ConfigProperties;
import com.ruomm.springcloud.paymentclient.dao.PaymentMapper;
import com.ruomm.springcloud.paymentclient.entry.PaymentEntity;
import com.ruomm.springcloud.pojo.CommonResponse;
import com.ruomm.springcloud.pojo.request.Payment;
import com.ruomm.springcloud.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author 牛牛-研发部-www.ruomm.com
 * @version 1.0
 * @copyright 像衍科技-idr.ai
 * @create 2024/1/30 0:17
 */
@Service
public class PaymentService {
    @Autowired
    PaymentMapper paymentMapper;
    @Autowired
    ConfigProperties configProperties;

    public CommonResponse<Long> create(Payment dept) {
        checkSerialNameRepeat(dept.getSerial());
        PaymentEntity entity = new PaymentEntity();
//        entity.setId(dept.getId());
        entity.setSerial(dept.getSerial());
        int dbResult = paymentMapper.insertSelective(entity);
        if (dbResult != 1) {
            return AppUtils.toNackCore("创建失败");
        } else {
            return AppUtils.toAckT(entity.getId());
        }
    }

    public CommonResponse<Payment> queryById(long id) {
        // 查找用户
        PaymentEntity entity = paymentMapper.selectByPrimaryKey(id);
        if (entity==null) {
            return AppUtils.toNackCore("查询失败");
        } else {
            Payment payment = new Payment();
            payment.setId(entity.getId());
            payment.setSerial(entity.getSerial());
            return AppUtils.toAck("查询成功",payment);
        }
    }



    // 判断用户昵称是否重复
    public boolean checkSerialNameRepeat(String serial) {
        Example example = new Example(PaymentEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("serial", serial);
        int count = paymentMapper.selectCountByExample(example);
        if (count > 0) {
            throw new WebAppException(com.ruomm.springcloud.utils.AppUtils.ERROR_CORE, String.format("名称(%s)重复。", serial));
        }
        return true;
    }
}
