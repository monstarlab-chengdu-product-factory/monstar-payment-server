package cn.monstar.payment.domain.model.dto;


/**
 * @author wangxianding
 * @version 1.0
 * @description API结果返回封装类
 * @date 2017-11-16 17:12:12
 */
public class APIResultDto {

    /**
     * 返回状态码 0.成功 1.失败
     */
    private int returnCode;

    /**
     * 返回信息
     */
    private String returnMsg;

    /**
     * 返回信息
     */
    private Object data;

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public APIResultDto() {
    }

    public APIResultDto(int returnCode, String returnMsg) {
        this();
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

    public APIResultDto(int returnCode, String returnMsg, Object data) {
        this(returnCode, returnMsg);
        this.data = data;
    }

}
