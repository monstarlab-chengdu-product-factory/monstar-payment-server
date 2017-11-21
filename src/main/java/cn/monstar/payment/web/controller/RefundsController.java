package cn.monstar.payment.web.controller;

import cn.monstar.payment.domain.model.dto.APIResultDto;
import cn.monstar.payment.domain.model.enums.ExceptionEnum;
import cn.monstar.payment.domain.util.APIResultDtoUtil;
import cn.monstar.payment.web.controller.form.RefundsFrom;
import cn.monstar.payment.web.exception.InvalidParamException;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	public APIResultDto sendRefunds(RefundsFrom refundsFrom) {
		System.out.println("==========================");
		//参数检查
		if (refundsFrom == null) {
			throw new InvalidParamException(ExceptionEnum.ILLEGALPARAMETER.getEnumValue(), ExceptionEnum.ILLEGALPARAMETER.getLabel());
		}
		logger.info(JSON.toJSONString(refundsFrom));
		//业务逻辑处理

		return APIResultDtoUtil.success();
	}


}