package cn.monstar.payment.web.exception;

import cn.monstar.payment.domain.model.dto.APIResultDto;
import cn.monstar.payment.domain.util.APIResultDtoUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author wangxianding
 * @version 1.0
 * @description 全局异常处理
 * @date 2017/11/21 下午4:57
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 无效参数异常
	 *
	 * @param e
	 * @param response
	 * @return
	 */
	@ExceptionHandler(InvalidParamException.class)
	@ResponseBody
	public APIResultDto exceptionHandler(InvalidParamException e, HttpServletResponse response) {
		return APIResultDtoUtil.failure(e.getCode(), e.getMessage());
	}

	/**
	 * 必填参数异常
	 *
	 * @param e
	 * @param response
	 * @return
	 */
	@ExceptionHandler(ParamRequiredException.class)
	@ResponseBody
	public APIResultDto exceptionHandler(ParamRequiredException e, HttpServletResponse response) {
		return APIResultDtoUtil.failure(e.getCode(), e.getMessage());
	}

	/**
	 * 检查异常
	 *
	 * @param e
	 * @param response
	 * @return
	 */
	@ExceptionHandler(CheckedException.class)
	@ResponseBody
	public APIResultDto exceptionHandler(CheckedException e, HttpServletResponse response) {
		return APIResultDtoUtil.failure(e.getCode(), e.getMessage());
	}

}
