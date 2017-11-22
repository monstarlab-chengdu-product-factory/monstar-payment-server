package cn.monstar.payment.web.controller;

import cn.monstar.payment.domain.model.dto.APIResultDto;
import cn.monstar.payment.domain.model.enums.ExceptionEnum;
import cn.monstar.payment.domain.util.APIResultDtoUtil;
import cn.monstar.payment.web.controller.form.RefundsFrom;
import cn.monstar.payment.web.exception.InvalidParamException;
import cn.monstar.payment.web.exception.ParamRequiredException;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangxianding
 * @version 1.0
 * @description 退款Controller
 * @date 2017/11/21 上午10:51
 */
@RestController
@RequestMapping("/payment/refunds")
public class RefundsController extends BaseController {

	@PostMapping("/sendRefund")
	public APIResultDto sendRefunds(@RequestBody RefundsFrom refundsFrom) {
		//参数检查
		if (refundsFrom == null) {
			throw new InvalidParamException();
		}
		if (StringUtils.isEmpty(refundsFrom.getOrderMoney())) {
			throw new ParamRequiredException("orderMoney");
		}
		if (StringUtils.isEmpty(refundsFrom.getPaymentNo())) {
			throw new ParamRequiredException("paymentNo");
		}
		if (StringUtils.isEmpty(refundsFrom.getRefundMoney())) {
			throw new ParamRequiredException("refundMoney");
		}
		if (StringUtils.isEmpty(refundsFrom.getRefundDescription())) {
			throw new ParamRequiredException("refundDescription");
		}
		logger.info("请求数据:{}", JSON.toJSON(refundsFrom));
		//业务逻辑处理


		return APIResultDtoUtil.success();
	}


}