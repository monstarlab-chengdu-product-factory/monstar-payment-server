package cn.monstar.payment.domain.model.enums;

/**
 * @author wangxianding
 * @version 1.0
 * @description 退款状态枚举
 * @date 2017/11/16 下午6:27
 */
public enum RefundStatusEnum implements BaseEnum {

	/**
	 * 退款处理中
	 */
	REFUNDPROCESSING(0, "退款处理中"),

	/**
	 * 退款成功
	 */
	SUCCESSFULREFUND(1, "退款成功"),

	/**
	 * 退款关闭
	 */
	REFUNDCLOSE(2, "退款关闭"),

	/**
	 * 退款异常
	 */
	REFUNDEXCEPTION(3, "退款异常");

	private int enumValue;

	private String lable;

	private RefundStatusEnum(int enumValue, String lable) {
		this.enumValue = enumValue;
		this.lable = lable;
	}

	@Override
	public int getEnumValue() {
		return 0;
	}

	@Override
	public String getLabel() {
		return null;
	}
}
