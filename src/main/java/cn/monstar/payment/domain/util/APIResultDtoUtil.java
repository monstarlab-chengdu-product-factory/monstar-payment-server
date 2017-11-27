package cn.monstar.payment.domain.util;

import cn.monstar.payment.domain.model.dto.APIResultDto;
import cn.monstar.payment.domain.model.enums.ResultStateEnum;

/**
 * @author wangxianding
 * @version 1.0
 * @description API结果返回工具封装类
 * @date 2017-11-16 17:39:34
 */
public class APIResultDtoUtil {

	/**
	 * 基础的API失败的返回封装
	 *
	 * @return
	 */
	public static APIResultDto failure() {
		return failure(null);
	}

	/**
	 * 基础的API失败的返回封装
	 *
	 * @param message 数据
	 * @return
	 */
	public static APIResultDto failure(String message) {
		return failure(ResultStateEnum.FAILD.getEnumValue(), message);
	}

	/**
	 * 基础的API失败的返回封装
	 *
	 * @param returnMsg 返回信息
	 * @param data      数据
	 * @return
	 */
	public static APIResultDto failure(String returnMsg, Object data) {
		return baseMethod(ResultStateEnum.FAILD.getEnumValue(), returnMsg, data);
	}

	/**
	 * 基础的API失败的返回封装
	 *
	 * @param returnCode
	 * @param returnMsg  返回信息
	 * @return
	 */
	public static APIResultDto failure(int returnCode, String returnMsg) {
		return baseMethod(returnCode, returnMsg, null);
	}

	/**
	 * 基础的API成功的返回封装
	 *
	 * @return
	 */
	public static APIResultDto success() {
		return success(null);
	}

	/**
	 * 基础的API成功的返回封装
	 *
	 * @param data 数据
	 * @return
	 */
	public static APIResultDto success(Object data) {
		return success(ResultStateEnum.SUCCESS.getLabel(), data);
	}

	/**
	 * 基础的API成功的返回封装
	 *
	 * @param returnMsg 返回信息
	 * @param data      数据
	 * @return
	 */
	public static APIResultDto success(String returnMsg, Object data) {
		return baseMethod(ResultStateEnum.SUCCESS.getEnumValue(), returnMsg, data);
	}

	/**
	 * 基础的API返回封装
	 *
	 * @param returnCode 返回码
	 * @param returnMsg  返回信息
	 * @param data       数据
	 * @return
	 */
	private static APIResultDto baseMethod(int returnCode, String returnMsg, Object data) {
		return new APIResultDto(returnCode, returnMsg, data);
	}
}
