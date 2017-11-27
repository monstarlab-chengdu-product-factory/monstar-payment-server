package cn.monstar.payment.domain.model.enums;

/**
 * @author wangxianding
 * @version 1.0
 * @description 结果状态码枚举
 * @date 2017/11/16 下午5:42
 */
public enum ResultStateEnum implements BaseEnum {

    SUCCESS(0, "成功"),
    FAILD(1, "失败");

    private int enumValue;

    private String lable;

    private ResultStateEnum(int enumValue, String lable) {
        this.enumValue = enumValue;
        this.lable = lable;
    }

    @Override
    public int getEnumValue() {
        return this.enumValue;
    }

    @Override
    public String getLabel() {
        return this.lable;
    }
}
