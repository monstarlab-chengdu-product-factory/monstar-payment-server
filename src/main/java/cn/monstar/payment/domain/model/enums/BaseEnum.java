package cn.monstar.payment.domain.model.enums;

/**
 * Created by shuai on 2017/6/12.
 */
public interface  BaseEnum<V> {

    /**
     * 获取枚举的值
     * @return
     */
    int getEnumValue();

    V getLabel();
}
