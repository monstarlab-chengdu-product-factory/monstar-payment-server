package cn.monstar.payment.domain.util.wechat.response;

import cn.monstar.payment.domain.util.wechat.Coupon;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxianding
 * @version 1.0
 * @description 微信申请退款返回结果https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=9_4&index=4
 * @date 2017/12/6 上午10:07
 */
@XStreamAlias("xml")
public class WxPayRefundResponse extends AbstractWxPayBaseResponse implements Serializable {

    private static final long serialVersionUID = -4608506396618104055L;

    /**
     * 微信订单号
     * String(32)
     * 是否必填: 是
     */
    @XStreamAlias("transaction_id")
    private String transactionId;

    /**
     * 商户订单号
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     * String(32)
     * 是否必填: 是
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    /**
     * 商户退款单号
     * 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     * String(64)
     * 是否必填: 是
     */
    @XStreamAlias("out_refund_no")
    private String outRefundNo;

    /**
     * 微信退款单号
     * String(32)
     * 是否必填: 是
     */
    @XStreamAlias("refund_id")
    private String refundId;

    /**
     * 退款金额
     * 退款总金额,单位为分,可以做部分退款
     * int
     * 是否必填: 是
     */
    @XStreamAlias("refund_fee")
    private Integer refundFee;

    /**
     * 应结退款金额
     * 去掉非充值代金券退款金额后的退款金额，退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
     * int
     * 是否必填：否
     */
    @XStreamAlias("settlement_refund_fee")
    private Integer settlementRefundFee;

    /**
     * 标价金额
     * 订单总金额，单位为分，只能为整数，详见https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=4_2
     * int
     * 是否必填；是
     */
    @XStreamAlias("total_fee")
    private Integer totalFee;

    /**
     * 应结订单金额
     * 去掉非充值代金券金额后的订单总金额，应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
     * int
     * 是否必填：否
     */
    @XStreamAlias("settlement_total_fee")
    private Integer settlementTotalFee;

    /**
     * 标价币种
     * 订单金额货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=4_2
     * String(8)
     * 是否必填: 否
     */
    @XStreamAlias("fee_type")
    private String feeType;

    /**
     * 现金支付金额
     * 现金支付金额，单位为分，只能为整数，详见https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=4_2
     * int
     * 是否必填: 是
     */
    @XStreamAlias("cash_fee")
    private Integer cashFee;

    /**
     * 现金支付币种
     * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=4_2
     * String(16)
     * 是否必填: 否
     */
    @XStreamAlias("cash_fee_type")
    private String cashFeeType;

    /**
     * 现金退款金额
     * 现金退款金额，单位为分，只能为整数，详见https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=4_2
     * int
     * 是否必填: 否
     */
    @XStreamAlias("cash_refund_fee")
    private String cashRefundFee;

    /**
     * 代金券退款总金额
     * 代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见https://pay.weixin.qq.com/wiki/doc/api/tools/sp_coupon.php?chapter=12_1
     * int
     * 是否必填：否
     */
    @XStreamAlias("coupon_refund_fee")
    private Integer couponRefundFee;

    /**
     * 退款代金券使用数量
     * int
     * 是否必填：否
     */
    @XStreamAlias("coupon_refund_count")
    private Integer couponRefundCount;

    private List<Coupon> coupons;

    /**
     * 通过xml组装coupons属性内容
     */
    public void composeCoupons() {
        if (this.couponRefundCount != null && this.couponRefundCount > 0) {
            this.coupons = new ArrayList<>();
            for (int i = 0; i < this.couponRefundCount; i++) {
                this.coupons.add(new Coupon(this.getXmlValue("xml/coupon_type_" + i),
                        this.getXmlValue("xml/coupon_id_" + i),
                        this.getXmlValueAsInt("xml/coupon_fee_" + i)));
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

    public Integer getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }

    public Integer getSettlementRefundFee() {
        return settlementRefundFee;
    }

    public void setSettlementRefundFee(Integer settlementRefundFee) {
        this.settlementRefundFee = settlementRefundFee;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getSettlementTotalFee() {
        return settlementTotalFee;
    }

    public void setSettlementTotalFee(Integer settlementTotalFee) {
        this.settlementTotalFee = settlementTotalFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Integer getCashFee() {
        return cashFee;
    }

    public void setCashFee(Integer cashFee) {
        this.cashFee = cashFee;
    }

    public String getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType;
    }

    public String getCashRefundFee() {
        return cashRefundFee;
    }

    public void setCashRefundFee(String cashRefundFee) {
        this.cashRefundFee = cashRefundFee;
    }

    public Integer getCouponRefundFee() {
        return couponRefundFee;
    }

    public void setCouponRefundFee(Integer couponRefundFee) {
        this.couponRefundFee = couponRefundFee;
    }

    public Integer getCouponRefundCount() {
        return couponRefundCount;
    }

    public void setCouponRefundCount(Integer couponRefundCount) {
        this.couponRefundCount = couponRefundCount;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
}
