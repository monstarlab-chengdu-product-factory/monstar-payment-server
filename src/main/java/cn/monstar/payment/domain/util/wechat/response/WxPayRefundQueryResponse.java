package cn.monstar.payment.domain.util.wechat.response;

import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangxianding
 * @version 1.0
 * @description 微信支付退款查询返回实体类
 * 详情见https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=9_5&index=5
 * @date 2017/11/28 上午11:35
 */
@XStreamAlias("xml")
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
    private Integer cashFee;

    /**
     * 退款笔数
     * int
     * 是否必填: 是
     */
    @XStreamAlias("refund_count")
    private Integer refundCount;

    private List<RefundRecord> refundRecords;

    /**
     * 组装生成退款记录属性的内容
     */
    public void composeRefundRecords() {
        if (this.refundCount != null && this.refundCount > 0) {
            this.refundRecords = Lists.newArrayList();

            for (int i = 0; i < this.refundCount; i++) {
                RefundRecord refundRecord = new RefundRecord();
                this.refundRecords.add(refundRecord);

                refundRecord.setOutRefundNo(this.getXmlValue("xml/out_refund_no_" + i));
                refundRecord.setRefundId(this.getXmlValue("xml/refund_id_" + i));
                refundRecord.setRefundChannel(this.getXmlValue("xml/refund_channel_" + i));
                refundRecord.setRefundFee(this.getXmlValueAsInt("xml/refund_fee_" + i));
                refundRecord.setSettlementRefundFee(this.getXmlValueAsInt("xml/settlement_refund_fee_" + i));
                refundRecord.setCouponType(this.getXmlValue("xml/coupon_type_" + i));
                refundRecord.setCouponRefundFee(this.getXmlValueAsInt("xml/coupon_refund_fee_" + i));
                refundRecord.setCouponRefundCount(this.getXmlValueAsInt("xml/coupon_refund_count_" + i));
                refundRecord.setRefundStatus(this.getXmlValue("xml/refund_status_" + i));
                refundRecord.setRefundRecvAccount(this.getXmlValue("xml/refund_recv_accout_" + i));

                if (refundRecord.getCouponRefundCount() == null || refundRecord.getCouponRefundCount() == 0) {
                    continue;
                }

                List<RefundRecord.RefundCoupon> coupons = Lists.newArrayList();
                for (int j = 0; j < refundRecord.getCouponRefundCount(); j++) {
                    coupons.add(
                            new RefundRecord.RefundCoupon(
                                    this.getXmlValue("xml/coupon_refund_id_" + i + "_" + j),
                                    this.getXmlValueAsInt("xml/coupon_refund_fee_" + i + "_" + j)
                            )
                    );
                }
            }

        }
    }

    public WxPayRefundQueryResponse() {
    }

    public WxPayRefundQueryResponse(int totalRefundCount, String transactionId, String outTradeNo, int totalFee, int settlementTotalFee, String feeType, int cashFee, int refundCount, List<RefundRecord> refundRecords) {
        this.totalRefundCount = totalRefundCount;
        this.transactionId = transactionId;
        this.outTradeNo = outTradeNo;
        this.totalFee = totalFee;
        this.settlementTotalFee = settlementTotalFee;
        this.feeType = feeType;
        this.cashFee = cashFee;
        this.refundCount = refundCount;
        this.refundRecords = refundRecords;
    }

    public int getTotalRefundCount() {
        return totalRefundCount;
    }

    public void setTotalRefundCount(int totalRefundCount) {
        this.totalRefundCount = totalRefundCount;
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

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public int getSettlementTotalFee() {
        return settlementTotalFee;
    }

    public void setSettlementTotalFee(int settlementTotalFee) {
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

    public Integer getRefundCount() {
        return refundCount;
    }

    public void setRefundCount(Integer refundCount) {
        this.refundCount = refundCount;
    }

    public List<RefundRecord> getRefundRecords() {
        return refundRecords;
    }

    public void setRefundRecords(List<RefundRecord> refundRecords) {
        this.refundRecords = refundRecords;
    }

    /**
     * 退款记录
     */
    public static class RefundRecord {
        /**
         * 商户退款单号
         * out_refund_no_$n
         * String(32)
         * 是否必填： 是
         */
        @XStreamAlias("out_refund_no")
        private String outRefundNo;

        /**
         * 微信退款单号
         * String(28)
         * 是否必填：是
         */
        @XStreamAlias("refund_id")
        private String refundId;

        /**
         * 退款渠道
         * ORIGINAL—原路退款
         * BALANCE—退回到余额
         * OTHER_BALANCE—原账户异常退到其他余额账户
         * OTHER_BANKCARD—原银行卡异常退到其他银行卡
         * String(16)
         * 是否必填：否
         */
        @XStreamAlias("refund_channel")
        private String refundChannel;

        /**
         * 申请退款金额
         * 退款总金额,单位为分,可以做部分退款
         * Int
         * 是否必填：是
         */
        @XStreamAlias("refund_fee")
        private Integer refundFee;

        /**
         * 退款金额
         * 退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
         * Int
         * 是否必填：否
         */
        @XStreamAlias("settlement_refund_fee")
        private Integer settlementRefundFee;

        /**
         * 退款资金来源
         * REFUND_SOURCE_RECHARGE_FUNDS
         * REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款/基本账户, REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款
         * String(30)
         * 是否必填：否
         */
        @XStreamAlias("refund_account")
        private String refundAccount;

        /**
         * 代金券类型
         * CASH--充值代金券 , NO_CASH---非充值代金券。订单使用代金券时有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_$0
         * int
         * 是否必填否
         */
        @XStreamAlias("coupon_type")
        private String couponType;

        /**
         * 代金券退款金额
         * 代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠
         * int
         * 是否必填：否
         */
        @XStreamAlias("coupon_refund_fee")
        private Integer couponRefundFee;

        /**
         * 退款代金券使用数量
         * 退款代金券使用数量 ,$n为下标,从0开始编号
         * int
         * 是否必填：否
         */
        @XStreamAlias("coupon_refund_count")
        private Integer couponRefundCount;

        private List<RefundCoupon> refundCoupons;

        /**
         * 退款状态
         * 退款状态：
         * SUCCESS—退款成功，
         * FAIL—退款失败，
         * PROCESSING—退款处理中，
         * CHANGE—转入代发，
         * 退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，需要商户人工干预，通过线下或者财付通转账的方式进行退款。
         * String(16)
         * 是否必填：是
         */
        @XStreamAlias("refund_status")
        private String refundStatus;
        /**
         * 退款入账账户
         * 招商银行信用卡0403
         * 取当前退款单的退款入账方，1）退回银行卡：{银行名称}{卡类型}{卡尾号}，2）退回支付用户零钱:支付用户零钱
         * 是否必填：是
         * String(64)
         */
        @XStreamAlias("refund_recv_accout")
        private String refundRecvAccount;

        public RefundRecord() {
        }

        public RefundRecord(String outRefundNo, String refundId, String refundChannel, Integer refundFee, Integer settlementRefundFee, String refundAccount, String couponType, Integer couponRefundFee, Integer couponRefundCount, List<RefundCoupon> refundCoupons, String refundStatus, String refundRecvAccount) {
            this.outRefundNo = outRefundNo;
            this.refundId = refundId;
            this.refundChannel = refundChannel;
            this.refundFee = refundFee;
            this.settlementRefundFee = settlementRefundFee;
            this.refundAccount = refundAccount;
            this.couponType = couponType;
            this.couponRefundFee = couponRefundFee;
            this.couponRefundCount = couponRefundCount;
            this.refundCoupons = refundCoupons;
            this.refundStatus = refundStatus;
            this.refundRecvAccount = refundRecvAccount;
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

        public String getRefundChannel() {
            return refundChannel;
        }

        public void setRefundChannel(String refundChannel) {
            this.refundChannel = refundChannel;
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

        public String getRefundAccount() {
            return refundAccount;
        }

        public void setRefundAccount(String refundAccount) {
            this.refundAccount = refundAccount;
        }

        public String getCouponType() {
            return couponType;
        }

        public void setCouponType(String couponType) {
            this.couponType = couponType;
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

        public List<RefundCoupon> getRefundCoupons() {
            return refundCoupons;
        }

        public void setRefundCoupons(List<RefundCoupon> refundCoupons) {
            this.refundCoupons = refundCoupons;
        }

        public String getRefundStatus() {
            return refundStatus;
        }

        public void setRefundStatus(String refundStatus) {
            this.refundStatus = refundStatus;
        }

        public String getRefundRecvAccount() {
            return refundRecvAccount;
        }

        public void setRefundRecvAccount(String refundRecvAccount) {
            this.refundRecvAccount = refundRecvAccount;
        }

        public static class RefundCoupon {


            /**
             * 退款代金券ID
             * 退款代金券ID, $n为下标，$m为下标，从0开始编号
             * String(20)
             * 是否必填：否
             */
            @XStreamAlias("coupon_refund_id")
            private String couponRefundId;

            /**
             * 单个退款代金券支付金额
             * 单个退款代金券支付金额, $n为下标，$m为下标，从0开始编号
             * Int
             * 是否必填：否
             */
            @XStreamAlias("coupon_refund_fee")
            private Integer couponRefundFee;

            public RefundCoupon() {
            }

            public RefundCoupon(String couponRefundId, Integer couponRefundFee) {
                this.couponRefundId = couponRefundId;
                this.couponRefundFee = couponRefundFee;
            }

            public String getCouponRefundId() {
                return couponRefundId;
            }

            public void setCouponRefundId(String couponRefundId) {
                this.couponRefundId = couponRefundId;
            }

            public Integer getCouponRefundFee() {
                return couponRefundFee;
            }

            public void setCouponRefundFee(Integer couponRefundFee) {
                this.couponRefundFee = couponRefundFee;
            }
        }

    }

}
