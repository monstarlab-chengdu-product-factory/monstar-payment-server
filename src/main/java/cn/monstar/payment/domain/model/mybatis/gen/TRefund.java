package cn.monstar.payment.domain.model.mybatis.gen;

import cn.monstar.payment.domain.model.enums.RefundStatusEnum;
import java.math.BigDecimal;
import java.util.Date;

public class TRefund {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund.refund_id
     *
     * @mbg.generated
     */
    private Long refundId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund.refund_no
     *
     * @mbg.generated
     */
    private String refundNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund.out_refund_no
     *
     * @mbg.generated
     */
    private String outRefundNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund.refund_description
     *
     * @mbg.generated
     */
    private String refundDescription;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund.order_money
     *
     * @mbg.generated
     */
    private BigDecimal orderMoney;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund.refund_money
     *
     * @mbg.generated
     */
    private BigDecimal refundMoney;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund.payment_id
     *
     * @mbg.generated
     */
    private Long paymentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund.refund_status
     *
     * @mbg.generated
     */
    private RefundStatusEnum refundStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund.fail_reason
     *
     * @mbg.generated
     */
    private String failReason;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund.up_dt
     *
     * @mbg.generated
     */
    private Date upDt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_refund.cre_dt
     *
     * @mbg.generated
     */
    private Date creDt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund.refund_id
     *
     * @return the value of t_refund.refund_id
     *
     * @mbg.generated
     */
    public Long getRefundId() {
        return refundId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund.refund_id
     *
     * @param refundId the value for t_refund.refund_id
     *
     * @mbg.generated
     */
    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund.refund_no
     *
     * @return the value of t_refund.refund_no
     *
     * @mbg.generated
     */
    public String getRefundNo() {
        return refundNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund.refund_no
     *
     * @param refundNo the value for t_refund.refund_no
     *
     * @mbg.generated
     */
    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo == null ? null : refundNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund.out_refund_no
     *
     * @return the value of t_refund.out_refund_no
     *
     * @mbg.generated
     */
    public String getOutRefundNo() {
        return outRefundNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund.out_refund_no
     *
     * @param outRefundNo the value for t_refund.out_refund_no
     *
     * @mbg.generated
     */
    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo == null ? null : outRefundNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund.refund_description
     *
     * @return the value of t_refund.refund_description
     *
     * @mbg.generated
     */
    public String getRefundDescription() {
        return refundDescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund.refund_description
     *
     * @param refundDescription the value for t_refund.refund_description
     *
     * @mbg.generated
     */
    public void setRefundDescription(String refundDescription) {
        this.refundDescription = refundDescription == null ? null : refundDescription.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund.order_money
     *
     * @return the value of t_refund.order_money
     *
     * @mbg.generated
     */
    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund.order_money
     *
     * @param orderMoney the value for t_refund.order_money
     *
     * @mbg.generated
     */
    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund.refund_money
     *
     * @return the value of t_refund.refund_money
     *
     * @mbg.generated
     */
    public BigDecimal getRefundMoney() {
        return refundMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund.refund_money
     *
     * @param refundMoney the value for t_refund.refund_money
     *
     * @mbg.generated
     */
    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund.payment_id
     *
     * @return the value of t_refund.payment_id
     *
     * @mbg.generated
     */
    public Long getPaymentId() {
        return paymentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund.payment_id
     *
     * @param paymentId the value for t_refund.payment_id
     *
     * @mbg.generated
     */
    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund.refund_status
     *
     * @return the value of t_refund.refund_status
     *
     * @mbg.generated
     */
    public RefundStatusEnum getRefundStatus() {
        return refundStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund.refund_status
     *
     * @param refundStatus the value for t_refund.refund_status
     *
     * @mbg.generated
     */
    public void setRefundStatus(RefundStatusEnum refundStatus) {
        this.refundStatus = refundStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund.fail_reason
     *
     * @return the value of t_refund.fail_reason
     *
     * @mbg.generated
     */
    public String getFailReason() {
        return failReason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund.fail_reason
     *
     * @param failReason the value for t_refund.fail_reason
     *
     * @mbg.generated
     */
    public void setFailReason(String failReason) {
        this.failReason = failReason == null ? null : failReason.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund.up_dt
     *
     * @return the value of t_refund.up_dt
     *
     * @mbg.generated
     */
    public Date getUpDt() {
        return upDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund.up_dt
     *
     * @param upDt the value for t_refund.up_dt
     *
     * @mbg.generated
     */
    public void setUpDt(Date upDt) {
        this.upDt = upDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_refund.cre_dt
     *
     * @return the value of t_refund.cre_dt
     *
     * @mbg.generated
     */
    public Date getCreDt() {
        return creDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_refund.cre_dt
     *
     * @param creDt the value for t_refund.cre_dt
     *
     * @mbg.generated
     */
    public void setCreDt(Date creDt) {
        this.creDt = creDt;
    }
}