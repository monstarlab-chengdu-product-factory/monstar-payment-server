package cn.monstar.payment.domain.model.enums;

/**
 * @author wangxianding
 * @version 1.0
 * @description 流水类型
 * @date 2017/11/22 下午1:47
 */
public enum BillTypeEnum implements BaseEnum {

    PAYMENT(0, "支付"),
    REFUNDS(1, "退款");

    private int enumValue;

    private String label;

    BillTypeEnum(int enumValue, String label) {
        this.enumValue = enumValue;
        this.label = label;
    }

    @Override
    public int getEnumValue() {
        return this.enumValue;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
