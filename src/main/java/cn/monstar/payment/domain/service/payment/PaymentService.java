package cn.monstar.payment.domain.service.payment;

import cn.monstar.payment.domain.model.dto.PayDto;
import cn.monstar.payment.domain.model.dto.PayQueryDto;
import cn.monstar.payment.domain.model.enums.AccessTypeEnum;
import cn.monstar.payment.domain.model.enums.PaymentStatusEnum;
import cn.monstar.payment.domain.model.mybatis.gen.TPayment;
import cn.monstar.payment.domain.service.BaseService;
import cn.monstar.payment.web.controller.form.PayForm;

import java.math.BigDecimal;

/**
 * @author zhangshuai
 * @version 1.0
 * @description
 * @date 2017/11/27 15:24
 */
public interface PaymentService extends BaseService<TPayment, Long> {

    /**
     * check paymentNo exist and orderMoney is right
     *
     * @param paymentNo
     * @param orderMoney
     * @return
     */
    boolean paymentCorrectCheck(String paymentNo, BigDecimal orderMoney);

    /**
     * create payment
     *
     * @param payForm
     */
    PayDto createPayment(PayForm payForm, AccessTypeEnum accessType);

    /**
     * @param paymentNo
     * @return
     */
    PayQueryDto paymentQuery(String paymentNo);

    /**
     * update payment to finish by paymentNo
     *
     * @param paymentNo
     * @param outTradeNo
     */
    void updateToFinish(String paymentNo, String outTradeNo);

}
