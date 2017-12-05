package cn.monstar.payment.domain.util.wechat;

/**
 * @author wangxianding
 * @version 1.0
 * @description 微信请求封装对象
 * @date 2017/12/4 下午6:28
 */
public class WxPayApiData {

    /**
     * 请求的url
     */
    private String url;

    /**
     * 请求字符串
     */
    private String requestStr;

    /**
     * 响应字符串
     */
    private String responseString;

    /**
     * 信息
     */
    private String message;

    public WxPayApiData() {
    }

    public WxPayApiData(String url, String requestStr, String responseString, String message) {
        this.url = url;
        this.requestStr = requestStr;
        this.responseString = responseString;
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestStr() {
        return requestStr;
    }

    public void setRequestStr(String requestStr) {
        this.requestStr = requestStr;
    }

    public String getResponseString() {
        return responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
