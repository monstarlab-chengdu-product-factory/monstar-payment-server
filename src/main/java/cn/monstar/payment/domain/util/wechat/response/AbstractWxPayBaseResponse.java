package cn.monstar.payment.domain.util.wechat.response;

import cn.monstar.payment.domain.util.xml.XStreamInitializer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.w3c.dom.Document;

/**
 * @author wangxianding
 * @version 1.0
 * @description 微信支付返回Base
 * @date 2017/11/27 下午5:41
 */
public abstract class AbstractWxPayBaseResponse {

    /**
     * 返回状态码：
     * SUCCESS/FAIL
     * String(16)
     * 是否必填: 是
     */
    @XStreamAlias("return_code")
    private String returnCode;

    /**
     * 返回信息：
     * 返回信息，如非空，为错误原因，签名失败，参数格式校验错误
     * String(128)
     * 是否必填: 否
     */
    @XStreamAlias("return_msg")
    private String returnMsg;

    // 当return_code为SUCCESS的时候，还会包括以下字段：

    /**
     * 业务结果：
     * SUCCESS/FAIL
     * SUCCESS退款申请接收成功，结果通过退款查询接口查询
     * FAIL 提交业务失败
     * String(16)
     * 是否必填: 是
     */
    @XStreamAlias("result_code")
    private String resultCode;

    /**
     * 错误代码
     * String(32)
     * 是否必填: 否
     */
    @XStreamAlias("err_code")
    private String errCode;

    /**
     * 错误代码描述
     * String(128)
     * 是否必填：否
     */
    @XStreamAlias("err_code_des")
    private String errCodeDes;

    /**
     * 公众账号ID：
     * 调用接口提交的公众账号ID
     * String(32)
     * 是否必填: 是
     */
    @XStreamAlias("appid")
    private String appid;

    /**
     * 商户号：
     * 调用接口提交的商户号
     * String(32)
     * 是否必填: 是
     */
    @XStreamAlias("mch_id")
    private String mchId;

    /**
     * 随机字符串：
     * 微信返回的随机字符串
     * String(32)
     * 是否必填: 是
     */
    @XStreamAlias("nonce_str")
    private String nonceStr;

    /**
     * 签名：
     * 微信返回的签名值
     * String(32)
     */
    @XStreamAlias("sign")
    private String sign;

    // 以下为辅助属性
    /**
     * xml字符串
     */
    private String xmlString;

    /**
     * xml的Document对象，用于解析xml文本
     */
    private Document xmlDoc;

    /**
     * 从xml字符串创建bean对象
     */
    public static <T extends AbstractWxPayBaseResponse> T fromXML(String xmlString, Class<T> clz) {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(clz);
        T result = (T) xstream.fromXML(xmlString);
        result.setXmlString(xmlString);
        return result;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getXmlString() {
        return xmlString;
    }

    public void setXmlString(String xmlString) {
        this.xmlString = xmlString;
    }

    public Document getXmlDoc() {
        return xmlDoc;
    }

    public void setXmlDoc(Document xmlDoc) {
        this.xmlDoc = xmlDoc;
    }
}
