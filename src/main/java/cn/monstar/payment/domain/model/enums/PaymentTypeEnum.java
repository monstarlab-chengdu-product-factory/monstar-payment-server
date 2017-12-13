package cn.monstar.payment.domain.model.enums;

/**
 * @author wangxianding
 * @version 1.0
 * @description 支付类型
 * @date 2017/11/22 下午1:54
 */
public enum PaymentTypeEnum implements BaseEnum {

    WECHAT(0, "微信支付"),
    ALIPAY(1, "支付宝支付"),
    APPLEPAY(2, "applepay"),
    UNIONPAY(3, "银联支付");

    private int enumValue;

    private String label;

    PaymentTypeEnum(int enumValue, String label) {
        this.enumValue = enumValue;
        this.label = label;
    }

    public static PaymentTypeEnum valueOf(Integer value) {
        PaymentTypeEnum[] enums = values();
        for (PaymentTypeEnum role : enums) {
            if (role.enumValue == value) {
                return role;
            }
        }
        throw new IllegalArgumentException("Illegal value");
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
