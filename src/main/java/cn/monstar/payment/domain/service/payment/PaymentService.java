package cn.monstar.payment.domain.service.payment;

import cn.monstar.payment.domain.model.mybatis.gen.TPayment;
import cn.monstar.payment.domain.service.BaseService;

/**
 * @author zhangshuai
 * @version 1.0
 * @description
 * @date 2017/11/27 15:24
 */
public interface PaymentService extends BaseService<TPayment, Long> {

    /**
     * 根据付款流水号获取付款记录
     *
     * @param paymentNo
     * @return
     */
    TPayment findByPaymentNo(String paymentNo);
}
