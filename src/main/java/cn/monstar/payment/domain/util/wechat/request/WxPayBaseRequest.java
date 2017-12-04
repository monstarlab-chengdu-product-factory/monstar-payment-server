package cn.monstar.payment.domain.util.wechat.request;

import cn.monstar.payment.config.WxConfig;
import cn.monstar.payment.domain.util.BeanUtil;
import cn.monstar.payment.domain.util.StringUtil;
import cn.monstar.payment.domain.util.encryption.SignUtils;
import cn.monstar.payment.domain.util.wechat.annotation.Required;
import cn.monstar.payment.domain.util.xml.XStreamInitializer;
import cn.monstar.payment.web.exception.wx.WxErrorException;
import cn.monstar.payment.web.exception.wx.WxPayException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.springframework.util.StringUtils;

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
    protected void checkFields() {
        try {
            BeanUtil.checkRequiredFields(this);
        } catch (WxErrorException e) {
            throw new WxPayException(e.getMessage());
        }

        //check other parameters
        this.checkConstraints();
    }

    protected void checkedAndSign (WxConfig wxConfig) {
        // not null check
        if (StringUtils.isEmpty(wxConfig.getAppid())) {
            throw new RuntimeException("appid is not allowed to be empty");
        }else if (StringUtils.isEmpty(wxConfig.getMchId())) {
            throw new RuntimeException("mchId is not allowed to be empty");
        }else if (StringUtils.isEmpty(wxConfig.getMchKey())) {
            throw new RuntimeException("mchKey is not allowed to be empty");
        }
        // config setting
        if (StringUtils.isEmpty(this.appid)) {
            setAppid(wxConfig.getAppid());
        }
        if (StringUtils.isEmpty(this.mchId)) {
            setMchId(wxConfig.getMchId());
        }
        if (StringUtils.isEmpty(this.nonceStr)) {
            setNonceStr(StringUtil.getNonceStr());
        }
        // do sign
        setSign(SignUtils.createSign(this, wxConfig.getMchKey(), this.signType));
    }

    public String toXML() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(this.getClass());
        return xstream.toXML(this);
    }

    /**
     * 检查约束情况
     */
    protected abstract void checkConstraints();

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
