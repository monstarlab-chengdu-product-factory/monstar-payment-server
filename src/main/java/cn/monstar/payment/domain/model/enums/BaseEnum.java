package cn.monstar.payment.domain.model.enums;

/**
 * @author zhangshuai
 * @version 1.0
 * @description BaseEnum
 * @date 2017/11/22 16:09
 */
public interface  BaseEnum<V> {

    /**
     * 获取枚举的值
     * @return
     */
    int getEnumValue();

    V getLabel();
}
