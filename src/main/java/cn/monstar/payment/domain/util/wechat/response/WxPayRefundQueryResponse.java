package cn.monstar.payment.domain.util.wechat.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * @author wangxianding
 * @version 1.0
 * @description 微信支付退款查询返回实体类
 * @date 2017/11/28 上午11:35
 */
public class WxPayRefundQueryResponse extends AbstractWxPayBaseResponse implements Serializable {

    private static final long serialVersionUID = 5643788057402412221L;

    /**
     * 订单总退款次数：
     * 订单总共已发生的部分退款次数，当请求参数传入offset后有返回
     * int
     * 是否必填: 是
     */
    @XStreamAlias("total_refund_count")
    private int totalRefundCount;

    /**
     * 微信订单号
     * String(32)
     * 是否必填: 是
     */
    @XStreamAlias("transaction_id")
    private String transactionId;

    /**
     * 商户订单号
     * String(32)
     * 是否必填: 是
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    /**
     * 订单金额
     * int
     * 是否必填: 是
     */
    @XStreamAlias("total_fee")
    private int totalFee;

    /**
     * 应结订单金额
     * int
     * 是否必填: 否
     */
    @XStreamAlias("settlement_total_fee")
    private int settlementTotalFee;

    /**
     * 货币种类
     * String(8)
     * 是否必填: 否
     */
    @XStreamAlias("fee_type")
    private String feeType;

    /**
     * 现金支付金额
     * int
     * 是否必填: 是
     */
    @XStreamAlias("cash_fee")
    private int cashFee;

    /**
     * 退款笔数
     * int
     * 是否必填: 是
     */
    @XStreamAlias("refund_count")
    private int refundCount;


    //TODO 未完待续

}
