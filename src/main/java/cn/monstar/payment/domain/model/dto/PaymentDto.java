package cn.monstar.payment.domain.model.dto;

import cn.monstar.payment.domain.model.enums.PaymentStatusEnum;
import cn.monstar.payment.domain.model.enums.PaymentTypeEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wangxianding
 * @version 1.0
 * @description 支付Dto
 * @date 2017/11/23 下午1:54
 */
public class PaymentDto implements Serializable {

	/**
	 * id
	 */
	private Long paymentId;

	/**
	 * 平台交易流水
	 */
	private String paymentNo;

	/**
	 * 支付类型: 0.微信 1.支付宝 2.ApplePay 3.银联支付
	 */
	private PaymentTypeEnum paymentType;

	/**
	 * 支付状态: 0.待支付 1.已支付 2.已关闭 3.支付失败
	 */
	private PaymentStatusEnum paymentStatus;

	/**
	 * 支付金额
	 */
	private BigDecimal orderMoney;

	/**
	 * 商品标题，用于各支付平台商品描述
	 */
	private String goodsInfo;

	/**
	 * 商品描述
	 */
	private String goodsDetails;

	/**
	 * 支付平台交易号
	 */
	private String outTradeNo;

	/**
	 * 操作员
	 */
	private String userNo;

	/**
	 * 附加数据
	 */
	private String description;

	/**
	 * 支付创建时间
	 */
	private Date orderCreDt;

	/**
	 * 支付失效时间：单位分钟
	 */
	private int expireTime;

	/**
	 * 更新时间
	 */
	private Date upDt;

	/**
	 * 创建时间
	 */
	private Date creDt;

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	public PaymentTypeEnum getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentTypeEnum paymentType) {
		this.paymentType = paymentType;
	}

	public PaymentStatusEnum getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatusEnum paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public BigDecimal getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(BigDecimal orderMoney) {
		this.orderMoney = orderMoney;
	}

	public String getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(String goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public String getGoodsDetails() {
		return goodsDetails;
	}

	public void setGoodsDetails(String goodsDetails) {
		this.goodsDetails = goodsDetails;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getOrderCreDt() {
		return orderCreDt;
	}

	public void setOrderCreDt(Date orderCreDt) {
		this.orderCreDt = orderCreDt;
	}

	public int getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(int expireTime) {
		this.expireTime = expireTime;
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
