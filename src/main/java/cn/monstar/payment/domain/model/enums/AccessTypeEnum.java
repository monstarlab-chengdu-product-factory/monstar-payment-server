package cn.monstar.payment.domain.model.enums;

/**
 * @author liuyiqian
 * @version 1.0
 * @description
 * @date 2017/12/11 上午11:36
 */
public enum AccessTypeEnum implements BaseEnum {

    H5(0, "H5支付"),
    QRCODE(1, "二维码支付");

    private int enumValue;

    private String label;

    AccessTypeEnum(int enumValue, String label) {
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
