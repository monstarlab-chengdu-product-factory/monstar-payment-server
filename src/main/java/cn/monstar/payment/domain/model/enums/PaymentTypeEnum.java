package cn.monstar.payment.domain.model.enums;

/**
 * @author wangxianding
 * @version 1.0
 * @description 支付类型
 * @date 2017/11/22 下午1:54
 */
public enum PaymentTypeEnum implements BaseEnum {

	/**
	 * 微信支付
	 */
	WECHAT(0, "微信支付"),

	/**
	 * 支付宝支付
	 */
	ALIPAY(1, "支付宝支付"),

	/**
	 * 苹果支付
	 */
	APPLEPAY(2, "applepay"),

	/**
	 * 银联支付
	 */
	UNIONPAY(3, "银联支付");

	private int enumValue;

	private String label;

	PaymentTypeEnum(int enumValue, String label) {
		this.enumValue = enumValue;
		this.label = label;
	}

	@Override
	public int getEnumValue() {
		return 0;
	}

	@Override
	public Object getLabel() {
		return null;
	}
}
