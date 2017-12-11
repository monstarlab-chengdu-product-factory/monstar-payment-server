package cn.monstar.payment.domain.util.wechat.request;

import cn.monstar.payment.config.MessageConfig;
import cn.monstar.payment.config.WxConfig;
import cn.monstar.payment.web.exception.BusinessException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangxianding
 * @version 1.0
 * @description 微信支付退款查询Request
 * @date 2017/11/27 下午5:16
 */
@XStreamAlias("xml")
public class WxPayRefundQueryRequest extends AbstractWxPayBaseRequest implements Serializable {

    /**
     * 微信订单号
     *
     * @description 微信订单号查询的优先级是：
     * refund_id > out_refund_no > transaction_id > out_trade_no
     * String(32)
     * 是否必填: 四选一
     */
    @XStreamAlias("transaction_id")
    private String transactionId;

    /**
     * 商户订单号
     *
     * @description 商户系统内部订单号，
     * 要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一
     * String(32)
     * 是否必填: 四选一
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    /**
     * 商户退款单号
     *
     * @description 商户系统内部的退款单号，
     * 商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     * String(64)
     * 是否必填: 四选一
     */
    @XStreamAlias("out_refund_no")
    private String outRefundNo;

    /**
     * 微信退款单号
     *
     * @description 微信生成的退款单号，
     * 在申请退款接口有返回
     * String(32)
     * 是否必填: 四选一
     */
    @XStreamAlias("refund_id")
    private String refundId;

    /**
     * 偏移量
     *
     * @description 偏移量，
     * 当部分退款次数超过10次时可使用，表示返回的查询结果从这个偏移量开始取记录
     * 15
     * 是否必填: 否
     */
    @XStreamAlias("offset")
    private Integer offset;

    @Override
    public void checkedAndSign(WxConfig wxConfig, MessageConfig messageConfig) {
        super.checkedAndSign(wxConfig, messageConfig);
    }

    @Override
    protected void checkConstraints(MessageConfig messageConfig) {
        List<String> params = Arrays.asList(transactionId, outTradeNo, outRefundNo, refundId);
        int count = 0;
        for (String param : params) {
            if (StringUtils.isNotBlank(param)) {
                count++;
                if (count > 2) {
                    throw new BusinessException(messageConfig.getE00010());
                }
            }
        }
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

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
