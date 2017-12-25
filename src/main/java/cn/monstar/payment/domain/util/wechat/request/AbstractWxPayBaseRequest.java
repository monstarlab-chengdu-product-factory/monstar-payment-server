package cn.monstar.payment.domain.util.wechat.request;

import cn.monstar.payment.config.MessageConfig;
import cn.monstar.payment.config.WxConfig;
import cn.monstar.payment.domain.util.BeanUtil;
import cn.monstar.payment.domain.util.encryption.WxSignUtils;
import cn.monstar.payment.domain.util.wechat.annotation.Required;
import cn.monstar.payment.domain.util.xml.XmlUtil;
import cn.monstar.payment.web.error.exception.BusinessException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wangxianding
 * @version 1.0
 * @description 微信支付基础请求参数
 * @date 2017/11/27 下午4:45
 */
public abstract class AbstractWxPayBaseRequest {

    /**
     * 公众账号ID
     * 微信支付分配的公众账号ID（企业号corpid即为此appId）
     * String(32)
     * 是否必填: 是
     */
    @Required
    @XStreamAlias("appid")
    private String appid;

    /**
     * 商户号
     * 微信支付分配的商户号
     * String(32)
     * 是否必填: 是
     */
    @Required
    @XStreamAlias("mch_id")
    private String mchId;

    /**
     * 随机字符串
     * 随机字符串，不长于32位。
     * String(32)
     * 是否必填: 是
     */
    @Required
    @XStreamAlias("nonce_str")
    private String nonceStr;

    /**
     * 签名
     * String(32)
     * 是否必填: 是
     */
    @Required
    @XStreamAlias("sign")
    private String sign;

    /**
     * 签名类型
     * 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
     * String(32)
     * 是否必填: 否
     */
    @XStreamAlias("sign_type")
    private String signType;


    /**
     * 检查请求参数内容，包括必填参数以及特殊约束
     */
    private void checkFields(MessageConfig messageConfig) {
        if (!BeanUtil.checkRequiredFields(this)) {
            throw new BusinessException(messageConfig.E00008);
        }
        //check other parameters
        this.checkConstraints(messageConfig);
    }

    protected void checkedAndSign(WxConfig wxConfig, MessageConfig messageConfig) {
        // config setting
        if (StringUtils.isBlank(this.appid)) {
            setAppid(wxConfig.appid);
        }
        if (StringUtils.isBlank(this.mchId)) {
            setMchId(wxConfig.mchId);
        }
        if (StringUtils.isBlank(this.nonceStr)) {
            setNonceStr(String.valueOf(System.currentTimeMillis()));
        }
        if (StringUtils.isBlank(this.signType)) {
            setSignType(WxSignUtils.MD5);
        }
        // check fileds
        this.checkFields(messageConfig);
        // do sign
        setSign(WxSignUtils.createSign(this, wxConfig.mchKey, this.signType));
    }

    public String toXML() {
        return XmlUtil.toXML(this);
    }

    /**
     * 检查约束情况
     */
    protected abstract void checkConstraints(MessageConfig messageConfig);

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
