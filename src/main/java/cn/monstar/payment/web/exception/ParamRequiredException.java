package cn.monstar.payment.web.exception;

import cn.monstar.payment.domain.model.enums.ExceptionEnum;

/**
 * @author wangxianding
 * @version 1.0
 * @description 必填参数异常
 * @date 2017/11/22 上午10:33
 */
public class ParamRequiredException extends BaseCustomerException{

	public ParamRequiredException(String filedName) {
		super(ExceptionEnum.PARAMREQUIRED.getEnumValue(), String.format(ExceptionEnum.PARAMREQUIRED.getLabel(),filedName));
	}

}
