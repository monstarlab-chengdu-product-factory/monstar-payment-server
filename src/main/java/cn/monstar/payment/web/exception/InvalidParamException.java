package cn.monstar.payment.web.exception;

import cn.monstar.payment.domain.model.enums.ExceptionEnum;

/**
 * @author wangxianding
 * @version 1.0
 * @description 无效参数异常
 * @date 2017/11/21 下午5:12
 */
public class InvalidParamException extends BaseCustomerException {

	public InvalidParamException() {
		super(ExceptionEnum.ILLEGALPARAMETER.getEnumValue(), ExceptionEnum.ILLEGALPARAMETER.getLabel());
	}

}
