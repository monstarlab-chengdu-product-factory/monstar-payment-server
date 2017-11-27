package cn.monstar.payment.web.controller;

import cn.monstar.payment.domain.model.dto.APIResultDto;
import cn.monstar.payment.domain.model.dto.RefundDto;
import cn.monstar.payment.domain.service.refunds.RefundService;
import cn.monstar.payment.domain.util.APIResultDtoUtil;
import cn.monstar.payment.web.controller.form.RefundForm;
import cn.monstar.payment.web.exception.InvalidParamException;
import cn.monstar.payment.web.exception.ParamRequiredException;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RefundController extends BaseController {

	@Autowired
	private RefundService refundService;

	/**
	 *
	 * @param refundForm 退款申请Form
	 * @return
	 */
	@PostMapping("/sendRefund")
	public APIResultDto sendRefunds(@RequestBody RefundForm refundForm) {
		//参数检查
		if (StringUtils.isEmpty(refundForm.getOrderMoney())) {
			throw new ParamRequiredException("orderMoney");
		}
		if (StringUtils.isEmpty(refundForm.getPaymentNo())) {
			throw new ParamRequiredException("paymentNo");
		}
		if (StringUtils.isEmpty(refundForm.getRefundMoney())) {
			throw new ParamRequiredException("refundMoney");
		}
		if (StringUtils.isEmpty(refundForm.getRefundDescription())) {
			throw new ParamRequiredException("refundDescription");
		}
		//业务逻辑处理
		RefundDto refundDto = refundService.refundApplication(refundForm);

		return APIResultDtoUtil.success(refundDto);
	}


}