package cn.monstar.payment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;

/**
 * @author wangxianding
 * @version 1.0
 * @description HttpClient配置
 * @date 2017/12/4 上午9:38
 */
@Component
@ConfigurationProperties(prefix = "httpClient")
@PropertySource("classpath:application-app.yml")
public class HttpClientConfig {

    /**
     * http请求连接超时时间
     */
    @Value("${httpConnectionTimeout}")
    public int httpConnectionTimeout;

    /**
     * http请求数据读取等待时间
     */
    @Value("${httpTimeout}")
    public int httpTimeout;

    private SSLContext sslContext;

    @Value("${httpProxyHost}")
    public String httpProxyHost;

    @Value("${httpProxyPort}")
    public int httpProxyPort;

    @Value("${httpProxyUsername}")
    public String httpProxyUsername;

    @Value("${httpProxyPassword}")
    public String httpProxyPassword;


    public SSLContext getSslContext() {
        return sslContext;
    }

    public void setSslContext(SSLContext sslContext) {
        this.sslContext = sslContext;
    }

}
