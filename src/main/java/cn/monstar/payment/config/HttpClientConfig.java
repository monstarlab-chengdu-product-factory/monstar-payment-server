package cn.monstar.payment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.net.ssl.SSLContext;

/**
 * @author wangxianding
 * @version 1.0
 * @description HttpClient配置
 * @date 2017/12/4 上午9:38
 */
@ConfigurationProperties(prefix = "httpClient")
public class HttpClientConfig {

    /**
     * http请求连接超时时间
     */
    @Value("${httpConnectionTimeout}")
    private int httpConnectionTimeout = 5000;

    /**
     * http请求数据读取等待时间
     */
    @Value("${httpTimeout}")
    private int httpTimeout = 10000;

    private SSLContext sslContext;

    @Value("${httpProxyHost}")
    private String httpProxyHost;

    @Value("${httpProxyPort}")
    private String httpProxyPort;

    @Value("${httpProxyUsername}")
    private String httpProxyUsername;

    @Value("${httpProxyPassword}")
    private String httpProxyPassword;

    public int getHttpConnectionTimeout() {
        return httpConnectionTimeout;
    }

    public void setHttpConnectionTimeout(int httpConnectionTimeout) {
        this.httpConnectionTimeout = httpConnectionTimeout;
    }

    public int getHttpTimeout() {
        return httpTimeout;
    }

    public void setHttpTimeout(int httpTimeout) {
        this.httpTimeout = httpTimeout;
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

    public String getHttpProxyPort() {
        return httpProxyPort;
    }

    public void setHttpProxyPort(String httpProxyPort) {
        this.httpProxyPort = httpProxyPort;
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
