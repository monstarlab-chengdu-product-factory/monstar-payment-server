package cn.monstar.payment.web.controller;

import cn.monstar.payment.domain.model.dto.APIResult;
import cn.monstar.payment.domain.model.dto.ApplyRefundResultDto;
import cn.monstar.payment.domain.model.dto.RefundDto;
import cn.monstar.payment.domain.service.refund.RefundService;
import cn.monstar.payment.web.controller.form.ApplyRefundForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
     * @param applyRefundForm 退款申请Form
     * @return
     */
    @PostMapping("/sendRefund")
    public APIResult sendRefunds(@RequestBody @Valid ApplyRefundForm applyRefundForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return APIResult.failure().setMessage(bindingResult.getFieldError().getField() + bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            //业务逻辑处理
            ApplyRefundResultDto applyRefundResultDto = refundService.refundApplication(applyRefundForm);
            return APIResult.success().setData(applyRefundResultDto);
        } catch (Exception e) {
            logger.error("退款申请出现错误:{}", e.getMessage());
            //TODO 错误描述待定
            return APIResult.failure().setMessage("申请失败");
        }
    }


}