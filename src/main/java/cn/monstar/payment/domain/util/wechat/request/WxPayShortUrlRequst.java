package cn.monstar.payment.domain.util.wechat.request;

import cn.monstar.payment.config.WxConfig;
import cn.monstar.payment.domain.util.wechat.annotation.Required;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * @author wangxianding
 * @version 1.0
 * @description 微信转换短连接请求类https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_9
 * @date 2017/12/6 下午5:18
 */
@XStreamAlias("xml")
public class WxPayShortUrlRequst extends AbstractWxPayBaseRequest implements Serializable {

    private static final long serialVersionUID = -4434109934564043522L;

    /**
     * URL链接
     * 需要转换的URL，签名用原串，传输需URLencode
     * String(512)
     * 是
     */
    @Required
    @XStreamAlias("long_url")
    private String longUrl;

    @Override
    public void checkedAndSign(WxConfig wxConfig) {
        super.checkedAndSign(wxConfig);
    }

    @Override
    protected void checkConstraints() {
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
