package cn.monstar.payment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author wangxianding
 * @version 1.0
 * @description 微信配置
 * @date 2017/11/29 下午3:49
 */
@Component
@ConfigurationProperties(prefix = "wx")
@PropertySource("classpath:application-app.yml")
public class WxConfig {

    /**
     * 公众账号ID
     */
    @Value("${appid}")
    public String appid;

    /**
     * 商户id
     */
    @Value("${mchId}")
    public String mchId;

    /**
     * 商户密钥
     */
    @Value("${mchKey}")
    public String mchKey;

    /**
     * 商户证书地址
     */
    @Value("${keyPath}")
    public String keyPath;

    /**
     * 支付回调地址
     */
    @Value("${notifyUrl}")
    public String notifyUrl;

}
