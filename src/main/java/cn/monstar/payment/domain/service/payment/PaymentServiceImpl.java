package cn.monstar.payment.domain.service.payment;

import cn.monstar.payment.domain.dao.mybatis.TPaymentMapper;
import cn.monstar.payment.domain.model.mybatis.gen.TPayment;
import cn.monstar.payment.domain.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhangshuai
 * @version 1.0
 * @description
 * @date 2017/11/27 15:35
 */
@Service
@Transactional
public class PaymentServiceImpl extends BaseServiceImpl<TPayment, Long, TPaymentMapper> implements PaymentService {

    @Autowired
    @Override
    public void setRepository(TPaymentMapper repository) {
        super.repository = repository;
    }

    @Override
    public TPayment findByPaymentNo(String paymentNo) {
        return super.repository.findByPaymentNo(paymentNo);
    }
}
