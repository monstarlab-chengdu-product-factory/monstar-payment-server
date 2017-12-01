package cn.monstar.payment.web.exception.wx;

/**
 * @author wangxianding
 * @version 1.0
 * @description
 * @date 2017/12/1 上午9:51
 */
public class WxErrorException extends BaseCustomerException {

    public WxErrorException(int state, String message) {
        super(state, message);
    }

}
