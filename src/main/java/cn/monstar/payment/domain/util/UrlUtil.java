package cn.monstar.payment.domain.util;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author wangxianding
 * @version 1.0
 * @description URL工具
 * @date 2017/12/6 下午5:31
 */
public class UrlUtil {

    /**
     * URL 编码处理
     *
     * @param url     需要编码的url
     * @param charset 字符集
     * @return 编码后的url
     */
    public static String encode(String url, String charset) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        if (StringUtils.isBlank(charset)) {
            charset = "UTF-8";
        }
        try {
            return URLEncoder.encode(url, charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
