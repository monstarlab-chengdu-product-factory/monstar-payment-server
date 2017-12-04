package cn.monstar.payment.domain.util.wechat.request;

import java.io.Serializable;

/**
 * @author wangxianding
 * @version 1.0
 * @description 微信支付退款查询Request
 * @date 2017/11/27 下午5:16
 */
public class WxPayRefundQueryRequest extends WxPayBaseRequest implements Serializable{

    /**
     * 微信订单号
     * @description 微信订单号查询的优先级是：
     *      refund_id > out_refund_no > transaction_id > out_trade_no
     * String(32)
     * 是否必填: 四选一
     */
    private String transactionId;

    /**
     * 商户订单号
     * @description 商户系统内部订单号，
     *   要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一
     * String(32)
     * 是否必填: 四选一
     */
    private String outTradeNo;

    /**
     * 商户退款单号
     * @description 商户系统内部的退款单号，
     *   商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     * String(64)
     * 是否必填: 四选一
     */
    private String outRefundNo;

    /**
     * 微信退款单号
     * @description 微信生成的退款单号，
     *   在申请退款接口有返回
     * String(32)
     * 是否必填: 四选一
     */
    private String refundId;

    /**
     * 偏移量
     * @description 偏移量，
     *   当部分退款次数超过10次时可使用，表示返回的查询结果从这个偏移量开始取记录
     * 15
     * 是否必填: 否
     */
    private Integer offset;

    @Override
    protected void checkConstraints() {

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
