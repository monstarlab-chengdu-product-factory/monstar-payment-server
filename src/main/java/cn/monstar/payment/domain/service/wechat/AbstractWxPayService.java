package cn.monstar.payment.domain.service.wechat;

import cn.monstar.payment.domain.util.wechat.WxPayApiData;
import cn.monstar.payment.domain.util.wechat.request.WxPayUnifiedOrderRequest;
import cn.monstar.payment.domain.util.wechat.response.WxPayUnifiedOrderResponese;
import cn.monstar.payment.web.exception.wx.WxPayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangxianding
 * @version 1.0
 * @description
 * @date 2017/12/4 下午6:19
 */
public abstract class AbstractWxPayService implements WxPayService {

    protected final String BASE_URL = "https://api.mch.weixin.qq.com";
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected static ThreadLocal<WxPayApiData> wxPayApiData = new ThreadLocal<>();

    @Override
    public WxPayUnifiedOrderResponese wxUnifiedOrder(WxPayUnifiedOrderRequest request) {
        this.post(BASE_URL + "/pay/unifiedorder", request.toXML(), false);
        return null;
    }

    /**
     * 发送post请求
     *
     * @param url        请求地址
     * @param requestStr 请求信息
     * @param useKey     是否使用证书
     * @return 返回请求结果字符串
     */
    protected String post(String url, String requestStr, boolean useKey) throws WxPayException {

        return null;
    }


}
