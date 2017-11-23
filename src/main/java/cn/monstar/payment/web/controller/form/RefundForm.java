package cn.monstar.payment.web.controller.form;

import java.io.Serializable;

/**
 * @author wangxianding
 * @version 1.0
 * @description 退款请求Form
 * @date 2017/11/21 上午9:50
 */
public class RefundForm implements Serializable{

	/**
	 * 平台交易流水号
	 */
	private String paymentNo;

	/**
	 * 支付总金额
	 */
	private String orderMoney;

	/**
	 * 退款金额
	 */
	private String refundMoney;

	/**
	 * 退款原因
	 */
	private String refundDescription;

	/**
	 * 退款流水号
	 */
	private String refundNo;


	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	public String getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}

	public String getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(String refundMoney) {
		this.refundMoney = refundMoney;
	}

	public String getRefundDescription() {
		return refundDescription;
	}

	public void setRefundDescription(String refundDescription) {
		this.refundDescription = refundDescription;
	}

	public String getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}
}