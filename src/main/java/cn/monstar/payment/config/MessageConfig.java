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
    private String E00001;

    @Value("${E00002}")
    private String E00002;

    @Value("${E00003}")
    private String E00003;

    @Value("${E00004}")
    private String E00004;

    @Value("${E00005}")
    private String E00005;

    @Value("${E00006}")
    private String E00006;

    @Value("${E00007}")
    private String E00007;

    @Value("${E00008}")
    private String E00008;

    @Value("${E00009}")
    private String E00009;

    @Value("${E00010}")
    private String E00010;

    @Value("${E00011}")
    private String E00011;

    @Value("${E00012}")
    private String E00012;

    @Value("${E00013}")
    private String E00013;

    @Value("${I00001}")
    private String I00001;

    public String getE00001() {
        return E00001;
    }

    public void setE00001(String e00001) {
        E00001 = e00001;
    }

    public String getE00002() {
        return E00002;
    }

    public void setE00002(String e00002) {
        E00002 = e00002;
    }

    public String getE00003() {
        return E00003;
    }

    public void setE00003(String e00003) {
        E00003 = e00003;
    }

    public String getE00004() {
        return E00004;
    }

    public void setE00004(String e00004) {
        E00004 = e00004;
    }

    public String getE00005() {
        return E00005;
    }

    public void setE00005(String e00005) {
        E00005 = e00005;
    }

    public String getE00006() {
        return E00006;
    }

    public void setE00006(String e00006) {
        E00006 = e00006;
    }

    public String getE00007() {
        return E00007;
    }

    public void setE00007(String e00007) {
        E00007 = e00007;
    }

    public String getE00008() {
        return E00008;
    }

    public void setE00008(String e00008) {
        E00008 = e00008;
    }

    public String getE00009() {
        return E00009;
    }

    public void setE00009(String e00009) {
        E00009 = e00009;
    }

    public String getE00010() {
        return E00010;
    }

    public void setE00010(String e00010) {
        E00010 = e00010;
    }

    public String getE00011() {
        return E00011;
    }

    public void setE00011(String e00011) {
        E00011 = e00011;
    }

    public String getE00012() {
        return E00012;
    }

    public void setE00012(String e00012) {
        E00012 = e00012;
    }

    public String getE00013() {
        return E00013;
    }

    public void setE00013(String e00013) {
        E00013 = e00013;
    }

    public String getI00001() {
        return I00001;
    }

    public void setI00001(String i00001) {
        I00001 = i00001;
    }
}
