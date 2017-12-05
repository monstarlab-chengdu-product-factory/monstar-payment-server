package cn.monstar.payment.domain.util.wechat.request;

import cn.monstar.payment.config.WxConfig;
import cn.monstar.payment.web.exception.wx.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wangxianding
 * @version 1.0
 * @description 微信订单查询请求实体类
 * @date 2017/12/5 上午11:38
 */
@XStreamAlias("xml")
public class WxPayOrderQueryRequest extends AbstractWxPayBaseRequest {

    /**
     * 微信订单号:
     * 微信的订单号，建议优先使用
     * String(32)
     * 是否必填: 二选一
     */
    @XStreamAlias("transaction_id")
    private String transactionId;

    /**
     * 商户订单号:
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     * String(32)
     * 是否必填: 二选一
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    @Override
    protected void checkFields() {
        super.checkFields();
    }

    @Override
    protected void checkConstraints() {
        if (StringUtils.isAllBlank(this.transactionId, this.outTradeNo)) {
            throw new WxPayException("transaction_id 和 out_trade_no 两者不能同时为空");
        }
    }

    @Override
    public void checkedAndSign(WxConfig wxConfig) {
        super.checkedAndSign(wxConfig);
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}
