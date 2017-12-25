package cn.monstar.payment.domain.util.wechat.request;

import cn.monstar.payment.config.WxConfig;
import cn.monstar.payment.domain.util.wechat.annotation.Required;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * @author wangxianding
 * @version 1.0
 * @description 关闭微信订单
 * 注意：订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟。
 * @date 2017/12/5 下午4:39
 */
@XStreamAlias("xml")
public class WxPayCloseOrderRequest extends AbstractWxPayBaseRequest implements Serializable {


    private static final long serialVersionUID = 2682312655245245200L;

    /**
     * 商户订单号
     * String(32)
     * 是否必填: 是
     */
    @Required
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    @Override
    public void checkedAndSign(WxConfig wxConfig) {
        super.checkedAndSign(wxConfig);
    }

    @Override
    protected void checkConstraints() {

    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}
