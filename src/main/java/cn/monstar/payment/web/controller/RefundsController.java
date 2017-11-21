package cn.monstar.payment.web.controller;

import cn.monstar.payment.domain.model.dto.APIResultDto;
import cn.monstar.payment.domain.util.APIResultDtoUtil;
import cn.monstar.payment.web.controller.form.RefundsFrom;
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
public class RefundsController extends BaseController{

	@PostMapping("/sendRefund")
	public APIResultDto sendRefunds(@RequestBody RefundsFrom refundsFrom){

		return APIResultDtoUtil.success(1);
	}


}