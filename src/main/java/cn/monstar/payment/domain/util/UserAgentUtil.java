package cn.monstar.payment.domain.util;

/**
 * @author liuyiqian
 * @version 1.0
 * @description
 * @date 2017/12/25 下午2:51
 */
public class UserAgentUtil {

    //定义移动端请求的所有可能类型
    private final static String[] agent = { "Android", "iPhone", "iPod","iPad", "Windows Phone", "MQQBrowser" };

    /**
     * 判断User-Agent 是不是来自于手机
     * @param ua
     * @return
     */
    public static boolean checkAgentIsMobile(String ua) {
        boolean flag = false;
        if (!ua.contains("Windows NT") || (ua.contains("Windows NT") && ua.contains("compatible; MSIE 9.0;"))) {
            // 排除 苹果桌面系统
            if (!ua.contains("Windows NT") && !ua.contains("Macintosh")) {
                for (String item : agent) {
                    if (ua.contains(item)) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }
}
