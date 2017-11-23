package cn.monstar.payment.web.exception;

import cn.monstar.payment.domain.model.enums.ExceptionEnum;

/**
 * @author wangxianding
 * @version 1.0
 * @description 检查异常：如状态不正常，类型不对
 * @date 2017/11/22 上午10:33
 */
public class CheckedException extends BaseCustomerException{

	public CheckedException(int errorCode,String message) {
		super(errorCode, message);
	}

}
