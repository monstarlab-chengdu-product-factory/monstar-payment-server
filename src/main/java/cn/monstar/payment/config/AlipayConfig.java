package cn.monstar.payment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author liuyiqian
 * @version 1.0
 * @description
 * @date 2017/12/8 下午3:58
 */
@Configuration
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class AlipayConfig {

    @Value("${alipay.server_url}")
    public String alipayServerUrl;
    @Value("${alipay.alipay_id}")
    public String alipayId;
    @Value("${alipay.private_key}")
    public String alipayPrivateKey;
    @Value("${alipay.public_key}")
    public String alipayPublicKey;
    @Value("${alipay.notify_url}")
    public String alipayNotifyUrl;
}
