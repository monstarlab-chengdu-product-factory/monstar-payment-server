package cn.monstar.payment.domain.util.xml;

import com.thoughtworks.xstream.XStream;

/**
 * @author wangxianding
 * @version 1.0
 * @description XML工具类
 * @date 2017/12/6 下午4:09
 */
public class XmlUtil {

    /**
     * 将xml bean转化为xml字符串
     *
     * @param xmlBean 带xml标签的bean
     * @return xml字符串
     */
    public static String toXML(Object xmlBean) {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(xmlBean.getClass());
        return xstream.toXML(xmlBean);
    }

}
