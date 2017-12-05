package cn.monstar.payment.domain.service.wechat;

import cn.monstar.payment.domain.util.wechat.request.WxPayUnifiedOrderRequest;
import cn.monstar.payment.domain.util.wechat.response.WxPayUnifiedOrderResponese;

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

}
