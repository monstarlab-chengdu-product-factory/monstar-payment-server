package cn.monstar.payment.web.exception;

import cn.monstar.payment.domain.model.dto.APIResult;
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

	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public APIResult exceptionHandler(BusinessException e, HttpServletResponse response) {
		return APIResult.failure().setMessage(e.getMessage());
	}


	/**
	 * 统一异常处理
	 * @param e
	 * @param response
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public APIResult exceptionHandler(Exception e, HttpServletResponse response) {
		return APIResult.failure().setMessage(e.getMessage());
	}

}
