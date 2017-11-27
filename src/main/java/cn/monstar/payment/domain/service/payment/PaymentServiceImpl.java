package cn.monstar.payment.domain.service.payment;

import cn.monstar.payment.domain.dao.mybatis.TPaymentMapper;
import cn.monstar.payment.domain.model.mybatis.gen.TPayment;
import cn.monstar.payment.domain.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangshuai
 * @version 1.0
 * @description
 * @date 2017/11/27 15:35
 */
public class PaymentServiceImpl extends BaseServiceImpl<TPayment, Long, TPaymentMapper> implements PaymentService {

    @Autowired
    @Override
    public void setRepository(TPaymentMapper repository) {
        super.repository = repository;
    }
}
