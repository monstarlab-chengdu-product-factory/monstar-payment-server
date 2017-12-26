package cn.monstar.payment.domain.util.wechat.response;

import cn.monstar.payment.config.MessageConfig;
import cn.monstar.payment.config.WxConfig;
import cn.monstar.payment.domain.util.encryption.WxSignUtils;
import cn.monstar.payment.domain.util.xml.XStreamInitializer;
import cn.monstar.payment.web.error.exception.BusinessException;
import com.google.common.base.Joiner;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * xml字符串转为map
     *
     * @return
     */
    public Map<String, String> toMap() {
        if (StringUtils.isBlank(this.xmlString)) {
            return null;
        }
        Map<String, String> result = new HashMap<>();
        Document doc = this.getXmlDoc();

        try {
            NodeList list = (NodeList) XPathFactory.newInstance().newXPath()
                    .compile("/xml/*")
                    .evaluate(doc, XPathConstants.NODESET);
            int len = list.getLength();
            for (int i = 0; i < len; i++) {
                result.put(list.item(i).getNodeName(), list.item(i).getTextContent());
            }
            return result;
        } catch (XPathExpressionException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验结果
     *
     * @param wxConfig     微信配置
     * @param signType     签名类型
     * @param checkSuccess 是否检查结果
     * @throws BusinessException
     */
    public void checkResult(WxConfig wxConfig, String signType, Boolean checkSuccess) throws BusinessException {
        Map<String, String> map = toMap();
        if (StringUtils.isNotBlank(this.sign) && !WxSignUtils.checkSign(map, wxConfig.mchKey)) {
            throw new BusinessException(MessageConfig.E00007);
        }

        if (checkSuccess) {
            StringBuilder errMsg = new StringBuilder();
            if (StringUtils.isNotBlank(this.resultCode)) {
                errMsg.append("返回码：").append(this.returnCode);
            }

            if (StringUtils.isNotBlank(this.returnMsg)) {
                errMsg.append(",返回信息：").append(this.returnMsg);
            }

            if (StringUtils.isNotBlank(this.resultCode)) {
                errMsg.append(",返回结果码：").append(this.resultCode);
            }

            if (StringUtils.isNotBlank(this.errCode)) {
                errMsg.append(",返回错误码：").append(this.errCode);
            }

            if (StringUtils.isNotBlank(this.errCodeDes)) {
                errMsg.append(",错误码描述：").append(this.errCodeDes);
            }
            throw new BusinessException(MessageConfig.E00016, new String[]{errMsg.toString()});
        }
    }

    /**
     * 获取xml中元素的值
     */
    protected String getXmlValue(String... path) {
        Document doc = this.getXmlDoc();
        String expression = String.format("/%s//text()", Joiner.on("/").join(path));
        try {
            return (String) XPathFactory
                    .newInstance()
                    .newXPath()
                    .compile(expression)
                    .evaluate(doc, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(MessageConfig.E00014 + expression);
        }
    }

    /**
     * 获取xml中元素的值，作为int值返回
     */
    protected Integer getXmlValueAsInt(String... path) {
        String result = this.getXmlValue(path);
        if (StringUtils.isBlank(result)) {
            return null;
        }

        return Integer.valueOf(result);
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

    /**
     * xml转Document对象，以便读取其元数据
     *
     * @return
     */
    public Document getXmlDoc() {
        if (this.xmlDoc != null) {
            return this.xmlDoc;
        }

        try {
            this.xmlDoc = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(this.xmlString.getBytes("UTF-8")));
            return this.xmlDoc;
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
            throw null;
        }
    }

    public void setXmlDoc(Document xmlDoc) {
        this.xmlDoc = xmlDoc;
    }
}
