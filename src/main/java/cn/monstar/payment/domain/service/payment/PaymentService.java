package cn.monstar.payment.domain.service.payment;

import cn.monstar.payment.domain.model.mybatis.gen.TPayment;
import cn.monstar.payment.domain.service.BaseService;

/**
 * @author wangxianding
 * @version 1.0
 * @description 支付Service
 * @date 2017/11/23 下午1:41
 */
public interface PaymentService extends BaseService<TPayment, Long> {

	/**
	 * 根据支付流水号获取支付信息
	 * @param paymentNo 支付流水号
	 * @return
	 */
	TPayment findByPaymentNo(String paymentNo);
}
