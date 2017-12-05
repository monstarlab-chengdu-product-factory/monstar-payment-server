package cn.monstar.payment.web.exception;

import cn.monstar.payment.web.exception.wx.BaseCustomerException;

/**
 * @author wangxianding
 * @version 1.0
 * @description
 * @date 2017/12/4 下午2:45
 */
public class BusinessException extends BaseCustomerException {

    public BusinessException(String message) {
        super(message);
    }

}
