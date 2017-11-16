package cn.monstar.payment.domain.model.dto;

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
	private int refundStatus;

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



}
