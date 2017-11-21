package cn.monstar.payment.web.exception;

/**
 * @author wangxianding
 * @version 1.0
 * @description 无效参数异常
 * @date 2017/11/21 下午5:12
 */
public class InvalidParamException extends RuntimeException {

	private int code;

	public InvalidParamException(int code, String message) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
