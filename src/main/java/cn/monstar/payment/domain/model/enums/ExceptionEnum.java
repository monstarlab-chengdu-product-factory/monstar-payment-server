package cn.monstar.payment.domain.model.enums;

/**
 * @author wangxianding
 * @version 1.0
 * @description 异常状态枚举
 * @date 2017/11/21 下午5:25
 */
public enum ExceptionEnum implements BaseEnum {

	/**
	 * 非法参数
	 */
	ILLEGALPARAMETER(100, "非法参数"),

	/**
	 * 必填参数
	 */
	PARAMREQUIRED(101, "%s参数必填"),

	NOF_FOUND_PAYMENT(40001, "不存在付款流水号");

	private int enumValue;

	private String label;

	private ExceptionEnum(int enumValue, String label) {
		this.enumValue = enumValue;
		this.label = label;
	}

	@Override
	public int getEnumValue() {
		return this.enumValue;
	}

	@Override
	public String getLabel() {
		return this.label;
	}
}
