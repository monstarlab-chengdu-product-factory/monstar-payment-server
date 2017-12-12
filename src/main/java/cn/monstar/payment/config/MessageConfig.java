package cn.monstar.payment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author wangxianding
 * @version 1.0
 * @description 异常警告信息配置
 * @date 2017/12/11 上午9:35
 */
@Component
@PropertySource("classpath:message.properties")
public class MessageConfig {

    @Value("${E00001}")
    public String E00001;

    @Value("${E00002}")
    public String E00002;

    @Value("${E00003}")
    public String E00003;

    @Value("${E00004}")
    public String E00004;

    @Value("${E00005}")
    public String E00005;

    @Value("${E00006}")
    public String E00006;

    @Value("${E00007}")
    public String E00007;

    @Value("${E00008}")
    public String E00008;

    @Value("${E00009}")
    public String E00009;

    @Value("${E00010}")
    public String E00010;

    @Value("${E00011}")
    public String E00011;

    @Value("${E00012}")
    public String E00012;

    @Value("${E00013}")
    public String E00013;

    @Value("${E00014}")
    public String E00014;

    @Value("${I00001}")
    public String I00001;

}
