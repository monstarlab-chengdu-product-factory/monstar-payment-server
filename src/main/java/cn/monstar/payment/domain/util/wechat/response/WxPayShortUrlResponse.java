package cn.monstar.payment.domain.util.wechat.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * @author wangxianding
 * @version 1.0
 * @description 微信长连接转短连接实体bean
 * @date 2017/12/6 下午5:21
 */
@XStreamAlias("xml")
public class WxPayShortUrlResponse extends AbstractWxPayBaseResponse implements Serializable{

    private static final long serialVersionUID = -7424366260981123394L;

    /**
     * URL链接
     * 转换后的URL
     * String(64)
     * 是
     */
    @XStreamAlias("short_url")
    private String shortUrl;

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
