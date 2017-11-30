package cn.monstar.payment.domain.util.encryption;

import cn.monstar.payment.domain.util.BeanUtil;
import cn.monstar.payment.domain.util.StringUtil;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author wangxianding
 * @version 1.0
 * @description 加密工具类
 * @date 2017/11/29 下午5:33
 */
public class SignUtils {

    private static final String MD5 = "MD5";
    private static final String HMACSHA256 = "HMAC-SHA256";

    /**
     * 生成签名
     * @param xmlBean 带有XStream的xmlbean
     * @param signKey 签名key
     * @param signType 签名类型
     * @return
     */
    public static String createSign(Object xmlBean, String signKey, String signType) {
        return createSign(BeanUtil.xmlBeanToMap(xmlBean), signKey, signType);
    }

    /**
     * 生成签名
     * @param param 签名的map
     * @param signKey 签名key
     * @param signType 签名类型
     * @return
     */
    public static String createSign(Map<String, String> param, String signKey, String signType){
        SortedMap<String, String> sortedMap = new TreeMap<>(param);

        //generator to sign StringBuilder
        StringBuilder toSignBuilder = new StringBuilder();
        for (String key : sortedMap.keySet()) {
            String value = sortedMap.get(key);
            if (!key.equalsIgnoreCase("sign") && StringUtils.isEmpty(value)) {
                toSignBuilder.append(key).append("=").append(value).append("&");
            }
        }
        toSignBuilder.append("key=").append(signKey);

        if (HMACSHA256.equalsIgnoreCase(signType)) {
            return hmacsha256(toSignBuilder.toString().getBytes(), signKey.getBytes());
        }else{
            return DigestUtils.md5DigestAsHex(toSignBuilder.toString().getBytes()).toUpperCase();
        }
    }

    /**
     * HMAC-SHA256 加密
     * @param data 需要加密的数据
     * @param key 加密的密钥
     * @return
     */
    public static String hmacsha256(byte[] data, byte[] key){
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            return byte2hex(mac.doFinal(data));
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String byte2hex(byte[] b){
        StringBuilder hs = new StringBuilder();
        String stmp;
        for(int n = 0;b!=null && n<b.length;n++){
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                hs.append('0');
            }
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }

    /**
     * 校验签名
     * @param xmlBean
     * @param signKey
     * @return
     */
    public static Boolean checkSign(Object xmlBean, String signKey){
        return checkSign(BeanUtil.xmlBeanToMap(xmlBean), signKey);
    }

    /**
     * 校验签名
     * @param param 需要校验的参数
     * @param signKey
     * @return
     */
    public static Boolean checkSign(Map<String, String> param, String signKey){
        return createSign(param, signKey, null).equals(param.get("sign"));
    }

}
