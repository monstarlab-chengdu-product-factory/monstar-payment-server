package cn.monstar.payment.web.exception;

import cn.monstar.payment.domain.model.dto.APIResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;

/**
 * @author wangxianding
 * @version 1.0
 * @description 全局异常处理
 * @date 2017/11/21 下午4:57
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

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

	/**
	 * 处理绑定数据异常
	 *
	 * @param ex
	 * @param headers
	 * @param status
	 * @param request
	 * @return
	 */
	@Override
	public ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(APIResult.failure().setData(ex.getBindingResult().getAllErrors()), HttpStatus.BAD_REQUEST);
	}

	/**
	 * MethodArgumentNotValidException异常
	 *
	 * @param ex
	 * @param headers
	 * @param status
	 * @param request
	 * @return
	 */
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(APIResult.failure().setData(ex.getBindingResult().getAllErrors()), HttpStatus.BAD_REQUEST);
	}

}
