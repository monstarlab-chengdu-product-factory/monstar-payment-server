package cn.monstar.payment.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wangxianding
 * @version 1.0
 * @description 微信配置
 * @date 2017/11/29 下午3:49
 */
@Component
@ConfigurationProperties(prefix = "wx")
public class WxConfig {

    /**
     * 公众账号ID
     */
    private String appid;

    /**
     * 商户id
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书地址
     */
    private String mchCertPath;

    /**
     * 支付回调地址
     */
    private String notifyUrl;

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

    public String getMchKey() {
        return mchKey;
    }

    public void setMchKey(String mchKey) {
        this.mchKey = mchKey;
    }

    public String getMchCertPath() {
        return mchCertPath;
    }

    public void setMchCertPath(String mchCertPath) {
        this.mchCertPath = mchCertPath;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
