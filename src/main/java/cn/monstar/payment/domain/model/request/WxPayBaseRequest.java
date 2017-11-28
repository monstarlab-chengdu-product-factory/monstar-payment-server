package cn.monstar.payment.domain.model.request;

/**
 * @author wangxianding
 * @version 1.0
 * @description 微信支付基础请求参数
 * @date 2017/11/27 下午4:45
 */
public abstract class WxPayBaseRequest {

    /**
     * 公众账号ID
     * 微信支付分配的公众账号ID（企业号corpid即为此appId）
     * String(32)
     * 是否必填: 是
     */
    private String appid;

    /**
     * 商户号
     * 微信支付分配的商户号
     * String(32)
     * 是否必填: 是
     */
    private String mchId;

    /**
     * 随机字符串
     * 随机字符串，不长于32位。
     * String(32)
     * 是否必填: 是
     */
    private String nonceStr;

    /**
     * 签名
     * String(32)
     * 是否必填: 是
     */
    private String sign;

    /**
     * 签名类型
     * 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
     * String(32)
     * 是否必填: 否
     * TODO 设置默认类型
     */
    private String signType;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }
}
