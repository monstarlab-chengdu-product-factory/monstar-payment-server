package cn.monstar.payment.domain.util.wechat.notify;

import cn.monstar.payment.domain.util.xml.XmlUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * @author wangxianding
 * @version 1.0
 * @description 微信消息通知返回
 * @date 2017/12/6 下午4:06
 */
@XStreamAlias("xml")
public class WxPayCommonResponse implements Serializable {

    private static final long serialVersionUID = -1495823604933749984L;

    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";

    @XStreamAlias("return_code")
    private String returnCode;

    @XStreamAlias("return_msg")
    private String returnMsg;

    public WxPayCommonResponse() {
    }

    public WxPayCommonResponse(String returnCode) {
        this.returnCode = returnCode;
    }

    public WxPayCommonResponse(String returnCode, String returnMsg) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

    /**
     * 处理结果成功时返回
     *
     * @return
     */
    public static String toSuccessXml() {
        return XmlUtil.toXML(new WxPayCommonResponse(SUCCESS));
    }

    /**
     * 处理结果失败时返回
     *
     * @param returnMsg 失败时返回的信息
     * @return
     */
    public static String toFailXml(String returnMsg) {
        return XmlUtil.toXML(new WxPayCommonResponse(FAIL, returnMsg));
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
}
