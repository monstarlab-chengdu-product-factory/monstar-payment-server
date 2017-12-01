package cn.monstar.payment.web.exception.wx;

/**
 * @author wangxianding
 * @version 1.0
 * @description 自定义异常基类
 * @date 2017/11/22 上午10:28
 */
public abstract class BaseCustomerException extends RuntimeException {

    private int code;

    public BaseCustomerException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
