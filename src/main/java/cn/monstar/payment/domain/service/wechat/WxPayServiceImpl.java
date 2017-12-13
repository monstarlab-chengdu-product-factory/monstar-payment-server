package cn.monstar.payment.domain.service.wechat;

import cn.monstar.payment.domain.model.enums.PaymentStatusEnum;
import cn.monstar.payment.domain.model.enums.PaymentTypeEnum;
import cn.monstar.payment.domain.model.mybatis.gen.TPayment;
import cn.monstar.payment.domain.service.payment.PaymentService;
import cn.monstar.payment.domain.util.constant.WxConstantUtil;
import cn.monstar.payment.domain.util.wechat.response.WxPayOrderQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangxianding
 * @version 1.0
 * @description 微信支付服务
 * @date 2017/12/5 上午10:32
 */
@Service
public class WxPayServiceImpl extends AbstractWxPayService {

    @Autowired
    private PaymentService paymentService;

    @Transactional
    @Override
    public TPayment tradeQuery(String paymentNo) {
        logger.info("create trade query of wechatpay request. paymentNo is :{}", paymentNo);
        TPayment payment = paymentService.findByPaymentNo(paymentNo);

        if (payment == null) {
            return null;
        }
        // 只处理微信支付的交易
        if (payment.getPaymentType() != PaymentTypeEnum.WECHAT) {
            return null;
        }

        // 检查支付状态。如果是已支付、支付完成、支付失败则直接返回
        if (payment.getPaymentStatus() == PaymentStatusEnum.CLOSED || payment.getPaymentStatus() == PaymentStatusEnum.PAID
                || payment.getPaymentStatus() == PaymentStatusEnum.PAYMENTFAILURE) {
            return payment;
        }
        // 同步获取微信服务器交易结果
        try {
            WxPayOrderQueryResponse wxPayOrderQueryResponse = this.wxOrderQuery(null, paymentNo);
            switch (wxPayOrderQueryResponse.getTradeType()) {
                case WxConstantUtil.TRADE_STATE_CLOSED:
                    payment.setPaymentStatus(PaymentStatusEnum.CLOSED);
                    break;
                case WxConstantUtil.TRADE_STATE_PAYERROR:
                    payment.setPaymentStatus(PaymentStatusEnum.PAYMENTFAILURE);
                    break;
                case WxConstantUtil.TRADE_STATE_SUCCESS:
                    payment.setPaymentStatus(PaymentStatusEnum.PAID);
                    payment.setOutTradeNo(wxPayOrderQueryResponse.getTransactionId());
                    break;
                default:
                    break;
            }
            paymentService.update(payment);
            return payment;
        } catch (Exception e) {
            logger.error("微信交易查询，获取微信服务器数据失败{}", e.getMessage());
            return null;
        }
    }
}
