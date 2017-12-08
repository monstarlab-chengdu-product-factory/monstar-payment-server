package cn.monstar.payment.domain.util.wechat.request;

import cn.monstar.payment.config.WxConfig;
import cn.monstar.payment.domain.util.wechat.annotation.Required;
import cn.monstar.payment.web.exception.wx.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author wangxianding
 * @version 1.0
 * @description 微信申请退款类https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_4
 * @date 2017/12/6 上午9:49
 */
@XStreamAlias("xml")
public class WxPayRefundRequest extends AbstractWxPayBaseRequest implements Serializable {

    private static final long serialVersionUID = 5789099636167061724L;

    /**
     * 微信订单号
     * 微信生成的订单号，在支付通知中有返回
     * String(32)
     * 是否必填：与out_trade_no二选一
     */
    @XStreamAlias("transaction_id")
    private String transactionId;

    /**
     * 商户订单号
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一
     * String(32)
     * 是否必填：与transaction_id二选一
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    /**
     * 商户退款单号
     * 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     * String(64)
     * 是否必填：是
     */
    @Required
    @XStreamAlias("out_refund_no")
    private String outRefundNo;

    /**
     * 订单金额
     * 订单总金额，单位为分，只能为整数，详情请见https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
     * int
     * 是否必填：是
     */
    @Required
    @XStreamAlias("total_fee")
    private Integer totalFee;

    /**
     * 退款金额
     * 退款总金额，订单总金额，单位为分，只能为整数，详见https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
     * int
     * 是否必填：是
     */
    @Required
    @XStreamAlias("refund_fee")
    private Integer refundFee;

    /**
     * 货币种类
     * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
     * String(8)
     * 是否必填：否
     */
    @XStreamAlias("refund_fee_type")
    private String refundFeeType;

    /**
     * 退款原因
     * 若商户传入，会在下发给用户的退款消息中体现退款原因
     * String(80)
     * 是否必填：否
     */
    @XStreamAlias("refund_desc")
    private String refundDesc;

    /**
     * 退款资金来源
     * 仅针对老资金流商户使用
     * REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）
     * REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
     * String(30)
     * 是否必填：否
     */
    @XStreamAlias("refund_account")
    private String refundAccount;

    public WxPayRefundRequest() {
    }

    public WxPayRefundRequest(Builder builder) {
        setAppid(builder.appid);
        setMchId(builder.mchId);
        setOutRefundNo(builder.outRefundNo);
        setOutTradeNo(builder.outTradeNo);
        setRefundAccount(builder.refundAccount);
        setRefundDesc(builder.refundDesc);
        setRefundFee(builder.refundFee);
        setRefundFeeType(builder.refundFeeType);
        setTotalFee(builder.totalFee);
        setTransactionId(builder.transactionId);
        setNonceStr(builder.nonceStr);
        setSignType(builder.signType);
    }


    @Override
    public void checkedAndSign(WxConfig wxConfig) {
        super.checkedAndSign(wxConfig);
    }

    @Override
    protected void checkConstraints() {
        if (StringUtils.isAllBlank(this.transactionId, this.outTradeNo)
                || StringUtils.isNoneBlank(this.transactionId, this.outTradeNo)) {
            throw new WxPayException("transaction_id和out_trade_no两者必须任传一个");
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

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }

    public String getRefundFeeType() {
        return refundFeeType;
    }

    public void setRefundFeeType(String refundFeeType) {
        this.refundFeeType = refundFeeType;
    }

    public String getRefundDesc() {
        return refundDesc;
    }

    public void setRefundDesc(String refundDesc) {
        this.refundDesc = refundDesc;
    }

    public String getRefundAccount() {
        return refundAccount;
    }

    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount;
    }

    public static class Builder {

        private String appid;
        private String mchId;
        private String nonceStr;
        private String sign;
        private String signType;
        private String transactionId;
        private String outTradeNo;
        private String outRefundNo;
        private Integer totalFee;
        private Integer refundFee;
        private String refundFeeType;
        private String refundDesc;
        private String refundAccount;

        public Builder() {
        }

        public WxPayRefundRequest newBuiler(){
            return new WxPayRefundRequest(this);
        }

        public Builder setAppid(String appid) {
            this.appid = appid;
            return this;
        }

        public Builder setMchId(String mchId) {
            this.mchId = mchId;
            return this;
        }

        public Builder setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
            return this;
        }

        public Builder setSign(String sign) {
            this.sign = sign;
            return this;
        }

        public Builder setSignType(String signType) {
            this.signType = signType;
            return this;
        }

        public Builder setTransactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder setOutTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
            return this;
        }

        public Builder setOutRefundNo(String outRefundNo) {
            this.outRefundNo = outRefundNo;
            return this;
        }

        public Builder setTotalFee(Integer totalFee) {
            this.totalFee = totalFee;
            return this;
        }

        public Builder setRefundFee(Integer refundFee) {
            this.refundFee = refundFee;
            return this;
        }

        public Builder setRefundFeeType(String refundFeeType) {
            this.refundFeeType = refundFeeType;
            return this;
        }

        public Builder setRefundDesc(String refundDesc) {
            this.refundDesc = refundDesc;
            return this;
        }

        public Builder setRefundAccount(String refundAccount) {
            this.refundAccount = refundAccount;
            return this;
        }
    }
}
