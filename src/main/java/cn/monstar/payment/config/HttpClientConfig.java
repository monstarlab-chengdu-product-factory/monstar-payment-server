package cn.monstar.payment.config;

import org.apache.commons.lang3.StringUtils;
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
    private int httpConnectionTimeout;

    /**
     * http请求数据读取等待时间
     */
    @Value("${httpTimeout}")
    private int httpTimeout;

    private SSLContext sslContext;

    @Value("${httpProxyHost}")
    private String httpProxyHost;

    @Value("${httpProxyPort}")
    private int httpProxyPort;

    @Value("${httpProxyUsername}")
    private String httpProxyUsername;

    @Value("${httpProxyPassword}")
    private String httpProxyPassword;

    public int getHttpConnectionTimeout() {
        return httpConnectionTimeout;
    }

    public void setHttpConnectionTimeout(String httpConnectionTimeout) {
        if (StringUtils.isBlank(httpConnectionTimeout)) {
            httpConnectionTimeout = "5000";
        }
        this.httpConnectionTimeout = Integer.valueOf(httpConnectionTimeout);
    }

    public int getHttpTimeout() {
        return httpTimeout;
    }

    public void setHttpTimeout(String httpTimeout) {
        if (StringUtils.isBlank(httpTimeout)) {
            httpTimeout = "10000";
        }
        this.httpTimeout = Integer.valueOf(httpTimeout);
    }

    public SSLContext getSslContext() {
        return sslContext;
    }

    public void setSslContext(SSLContext sslContext) {
        this.sslContext = sslContext;
    }

    public String getHttpProxyHost() {
        return httpProxyHost;
    }

    public void setHttpProxyHost(String httpProxyHost) {
        this.httpProxyHost = httpProxyHost;
    }

    public int getHttpProxyPort() {
        return httpProxyPort;
    }

    public void setHttpProxyPort(String httpProxyPort) {
        if (!StringUtils.isBlank(httpProxyPort)) {
            httpProxyPort = "0";
        }
        this.httpProxyPort = Integer.valueOf(httpProxyPort);
    }

    public String getHttpProxyUsername() {
        return httpProxyUsername;
    }

    public void setHttpProxyUsername(String httpProxyUsername) {
        this.httpProxyUsername = httpProxyUsername;
    }

    public String getHttpProxyPassword() {
        return httpProxyPassword;
    }

    public void setHttpProxyPassword(String httpProxyPassword) {
        this.httpProxyPassword = httpProxyPassword;
    }
}
