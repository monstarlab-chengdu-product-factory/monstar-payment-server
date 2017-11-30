package cn.monstar.payment.domain.util.wechat.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangxianding
 * @version 1.0
 * @description 必填注解
 * @date 2017/11/30 上午11:16
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Required {
}
