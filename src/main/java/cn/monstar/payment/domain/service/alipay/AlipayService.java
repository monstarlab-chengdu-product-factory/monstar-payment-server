package cn.monstar.payment.domain.service.alipay;

import cn.monstar.payment.domain.model.mybatis.gen.TPayment;
import cn.monstar.payment.domain.model.mybatis.gen.TRefund;

/**
 * @author zhangshuai
 * @version 1.0
 * @description
 * @date 2017/11/27 14:49
 */
public interface AlipayService {

    /**
     * wap支付
     *
     * @param paymentNo
     * @return
     * @throws Exception
     */
    String tradeWapPay(String paymentNo) throws Exception;

    /**
     * page支付
     *
     * @param paymentNo
     * @return
     * @throws Exception
     */
    String tradePagePay(String paymentNo) throws Exception;

    /**
     * 交易查询
     *
     * @param paymentNo
     */
    TPayment tradeQuery(String paymentNo);

    /**
     * 退款
     *
     * @param refundNo
     */
    boolean tradeRefund(String refundNo);

    /**
     * 退款查询
     *
     * @param refundNo
     */
    TRefund refundQuery(String refundNo);

}
