package cn.monstar.payment.domain.util.wechat.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author wangxianding
 * @version 1.0
 * @description
 * @date 2017/12/4 下午4:11
 */
@XStreamAlias("xml")
public class WxPayUnifiedOrderResponese extends AbstractWxPayBaseResponse{

    /**
     * 设备号
     * String(32)
     * 是否必填：否
     */
    @XStreamAlias("device_info")
    private String deviceInfo;

    /**
     * 交易类型:
     *   交易类型，取值为：JSAPI，NATIVE，APP等
     * String(16)
     * 是否必填：是
     */
    @XStreamAlias("trade_type")
    private String tradeType;

    /**
     * 预支付交易会话标识:
     *   微信生成的预支付会话标识，用于后续接口调用中使用，该值有效期为2小时
     * String(64)
     * 是否必填: 是
     */
    @XStreamAlias("prepay_id")
    private String prepayId;

    /**
     * 二维码链接:
     *  trade_type为NATIVE时有返回，用于生成二维码，展示给用户进行扫码支付
     * String(64)
     * 是否必填: 否
     */
    @XStreamAlias("code_url")
    private String codeUrl;

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }
}
