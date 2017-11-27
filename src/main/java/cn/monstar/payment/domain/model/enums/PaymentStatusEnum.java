package cn.monstar.payment.domain.model.enums;

/**
 * @author wangxianding
 * @version 1.0
 * @description 支付状态
 * @date 2017/11/22 下午2:03
 */
public enum PaymentStatusEnum implements BaseEnum {

    UNPAID(0, "待支付"),
    PAID(1, "已支付"),
    CLOSED(2, "已关闭"),
    PAYMENTFAILURE(3, "支付失败");

    private int enumValue;

    private String label;

    PaymentStatusEnum(int enumValue, String label) {
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
