package cn.monstar.payment.domain.model.enums;

/**
 * @author wangxianding
 * @version 1.0
 * @description 异常状态枚举
 * @date 2017/11/21 下午5:25
 */
public enum ExceptionEnum implements BaseEnum {

    ILLEGALPARAMETER(100, "非法参数"),

    PAYMENT_NOT_FOUND(40001, "%s付款单未找到相应的付款单"),

    PAYMENT_STATUS_ERROR(50001, "%s付款单状态异常"),


    PARAMREQUIRED(101, "%s参数必填");

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
