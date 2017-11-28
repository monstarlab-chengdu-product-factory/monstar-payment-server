package cn.monstar.payment.domain.model.enums;

/**
 * @author wangxianding
 * @version 1.0
 * @description 退款状态枚举
 * @date 2017/11/16 下午6:27
 */
public enum RefundStatusEnum implements BaseEnum {

    REFUNDPROCESSING(0, "退款处理中"),
    SUCCESSFULREFUND(1, "退款成功"),
    REFUNDCLOSE(2, "退款关闭"),
    REFUNDEXCEPTION(3, "退款异常");

    private int enumValue;

    private String label;

    private RefundStatusEnum(int enumValue, String label) {
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
