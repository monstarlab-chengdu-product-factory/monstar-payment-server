package cn.monstar.payment.domain.model.dto;

import cn.monstar.payment.domain.model.enums.RefundStatusEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wangxianding
 * @version 1.0
 * @description 退款Dto
 * @date 2017/11/16 下午6:17
 */
public class RefundsDto {

	/**
	 * 退款
	 */
	private Long refundId;

	/**
	 * 退款流水号
	 */
	private String refundNo;

	/**
	 * 第三方支付平台退款流水号
	 */
	private String outRefundNo;

	/**
	 * 退款原因
	 */
	private String refundDescription;

	/**
	 * 订单金额
	 */
	private BigDecimal orderMoney;

	/**
	 * 退款金额
	 */
	private BigDecimal refundMoney;

	/**
	 * 付款流水id
	 */
	private Long paymentId;

	/**
	 * 退款状态
	 */
	private RefundStatusEnum refundStatus;

	/**
	 * 失败原因
	 */
	private String failReason;

	/**
	 * 更新时间
	 */
	private Date upDt;

	/**
	 * 创建时间
	 */
	private Date creDt;

	public Long getRefundId() {
		return refundId;
	}

	public void setRefundId(Long refundId) {
		this.refundId = refundId;
	}

	public String getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}

	public String getOutRefundNo() {
		return outRefundNo;
	}

	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}

	public String getRefundDescription() {
		return refundDescription;
	}

	public void setRefundDescription(String refundDescription) {
		this.refundDescription = refundDescription;
	}

	public BigDecimal getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(BigDecimal orderMoney) {
		this.orderMoney = orderMoney;
	}

	public BigDecimal getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(BigDecimal refundMoney) {
		this.refundMoney = refundMoney;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public RefundStatusEnum getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(RefundStatusEnum refundStatus) {
		this.refundStatus = refundStatus;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public Date getUpDt() {
		return upDt;
	}

	public void setUpDt(Date upDt) {
		this.upDt = upDt;
	}

	public Date getCreDt() {
		return creDt;
	}

	public void setCreDt(Date creDt) {
		this.creDt = creDt;
	}
}
