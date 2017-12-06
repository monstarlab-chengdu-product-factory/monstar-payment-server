package cn.monstar.payment.domain.service.wechat;

import cn.monstar.payment.domain.util.wechat.notify.WxPayNotifyRequest;
import cn.monstar.payment.domain.util.wechat.request.WxPayRefundRequest;
import cn.monstar.payment.domain.util.wechat.request.WxPayUnifiedOrderRequest;
import cn.monstar.payment.domain.util.wechat.response.*;

/**
 * @author wangxianding
 * @version 1.0
 * @description 微信支付服务接口
 * @date 2017/12/4 上午9:36
 */
public interface WxPayService {

    /**
     * 微信支付统一下单接口
     * 详情请见：https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
     *
     * @param request 微信统一下单对象
     * @return 微信统一下单结果
     */
    WxPayUnifiedOrderResponese wxUnifiedOrder(WxPayUnifiedOrderRequest request);

    /**
     * 解析支付结果通知
     *
     * @param notifyString 支付结果通知字符串
     * @return 支付结果
     */
    WxPayNotifyRequest parseNofifyResult(String notifyString);

    /**
     * 微信支付订单的查询
     * 详情见https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=9_2&index=2
     *
     * @param outTradeNo    商户订单号
     * @param transactionId 微信订单号
     * @return 微信支付订单的查询结果
     */
    WxPayOrderQueryResponse wxOrderQuery(String transactionId, String outTradeNo);

    /**
     * 微信关闭订单接口
     * 详情见https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=9_3&index=3
     *
     * @param outTradeNo 商户订单号
     * @return
     */
    WxPayCloseOrderResponse wxCloseOrder(String outTradeNo);

    /**
     * 申请退款
     * 详情见https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=9_4&index=4
     *
     * @param request
     * @return
     */
    WxPayRefundResponse wxSendRefund(WxPayRefundRequest request);

    /**
     * 退款查询
     * 详情见https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=9_5&index=5
     *
     * @param transactionId 微信单号
     * @param outTradeNo    商户单号
     * @param outRefundNo   商户退款单号
     * @param refundId      微信退款单号
     * @return
     */
    WxPayRefundQueryResponse wxRefundQuery(String transactionId, String outTradeNo, String outRefundNo, String refundId);

    /**
     * 长链接转换为短链接
     * @param longUrl 长链接
     * @return
     */
    WxPayShortUrlResponse wxLongUrlToShortUrl(String longUrl);


}
