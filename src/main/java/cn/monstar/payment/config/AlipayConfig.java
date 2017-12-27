package cn.monstar.payment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author liuyiqian
 * @version 1.0
 * @description
 * @date 2017/12/8 下午3:58
 */
@Component
@ConfigurationProperties(prefix = "alipay")
@PropertySource(value = "classpath:application-app.yml")
public class AlipayConfig {

    /**
     * 支付宝网关
     */
    @Value("${serverUrl}")
    public String serverUrl;

    /**
     * 应用ID
     */
    @Value("${alipayId}")
    public String alipayId;

    /**
     * 应用私钥
     */
    @Value("${privateKey}")
    public String privateKey;

    /**
     * 支付宝公钥
     */
    @Value("${publicKey}")
    public String publicKey;

    /**
     * 支付回调地址
     */
    @Value("${notifyUrl}")
    public String notifyUrl;

    /**
     * 同步地址
     */
    @Value("${returnUrl}")
    public String returnUrl;
}
