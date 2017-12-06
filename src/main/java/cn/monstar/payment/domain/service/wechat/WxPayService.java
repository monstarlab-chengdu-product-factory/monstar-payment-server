package cn.monstar.payment.domain.service.wechat;

import cn.monstar.payment.domain.util.wechat.request.*;
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
     * 微信支付订单的查询
     * 详情见https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=9_2&index=2
     *
     * @param request 微信支付订单的查询
     * @return 微信支付订单的查询结果
     */
    WxPayOrderQueryResponse wxOrderQuery(WxPayOrderQueryRequest request);

    /**
     * 微信关闭订单接口
     * 详情见https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=9_3&index=3
     *
     * @param request
     * @return
     */
    WxPayCloseOrderResponse wxCloseOrder(WxPayCloseOrderRequest request);

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
     * @param request
     * @return
     */
    WxPayRefundQueryResponse wxRefundQuery(WxPayRefundQueryRequest request);

}
