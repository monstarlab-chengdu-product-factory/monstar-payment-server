package cn.monstar.payment.domain.util;

import cn.monstar.payment.domain.util.wechat.annotation.Required;
import cn.monstar.payment.web.exception.ParamRequiredException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangxianding
 * @version 1.0
 * @description
 * @date 2017/11/29 下午5:46
 */
public class BeanUtil {

    /**
     * 将xmlbean转换为Map
     * @param xmlBean
     * @return
     */
    public static Map<String, String> xmlBeanToMap(Object xmlBean){
        Map<String, String> result = new HashMap<>();
        List<Field> fields = Arrays.asList(xmlBean.getClass().getDeclaredFields());
        if (!xmlBean.getClass().getSuperclass().getName().equalsIgnoreCase("Object")) {
            fields.addAll(Arrays.asList(xmlBean.getClass().getSuperclass().getDeclaredFields()));
        }

        for (Field field: fields) {
            try {
                boolean isAccessible = field.isAccessible();
                field.setAccessible(true);
                if (field.get(xmlBean) == null) {
                    field.setAccessible(isAccessible);
                    continue;
                }

                if (field.isAnnotationPresent(XStreamAlias.class)){
                    result.put(field.getAnnotation(XStreamAlias.class).value(), field.get(xmlBean).toString());
                }
                field.setAccessible(isAccessible);
            }catch (SecurityException | IllegalArgumentException | IllegalAccessException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void checkRequiredFields(Object object){
        List<Field> fields = Arrays.asList(object.getClass().getDeclaredFields());
        if (!object.getClass().getSuperclass().getName().equalsIgnoreCase("Object")) {
            fields.addAll(Arrays.asList(object.getClass().getSuperclass().getDeclaredFields()));
        }

        try {
            for (Field field : fields) {
                boolean isAccessible = field.isAccessible();
                field.setAccessible(true);
                Required required = field.getAnnotation(Required.class);
                if (required != null) {
                    String value = field.get(object).toString();
                    field.setAccessible(isAccessible);
                    if (StringUtils.isEmpty(value)){
                        throw new ParamRequiredException(field.getName());
                    }
                }
                field.setAccessible(isAccessible);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}
