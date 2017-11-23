package cn.monstar.payment.domain.service.payment;

import cn.monstar.payment.domain.dao.mybatis.TPaymentMapper;
import cn.monstar.payment.domain.model.mybatis.gen.TPayment;
import cn.monstar.payment.domain.service.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangxianding
 * @version 1.0
 * @description 支付Service
 * @date 2017/11/23 下午1:42
 */
@Service
@Transactional
public class PaymentServiceImpl extends BaseServiceImpl<TPayment, Long, TPaymentMapper> implements PaymentService {

	@Override
	public void setRepository(TPaymentMapper repository) {
		super.repository = repository;
	}

	@Override
	public TPayment findByPaymentNo(String paymentNo) {
		return this.repository.findByPaymentNo(paymentNo);
	}
}
