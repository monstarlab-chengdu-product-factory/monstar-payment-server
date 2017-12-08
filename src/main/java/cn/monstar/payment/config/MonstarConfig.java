package cn.monstar.payment.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author wangxianding
 * @version 1.0
 * @description
 * @date 2017/11/28 下午1:36
 */
@Component
@ConfigurationProperties(prefix = "monstar")
@PropertySource("classpath:application-app.yml")
public class MonstarConfig {

    /**
     * 是否启用沙箱环境
     */
    private boolean sandboxnew;

    public void setSandboxnew(Boolean sandboxnew) {
        this.sandboxnew = sandboxnew;
    }

    public Boolean getSandboxnew() {
        return sandboxnew;
    }
}
