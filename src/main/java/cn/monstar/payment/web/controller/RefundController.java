package cn.monstar.payment.web.controller;

import cn.monstar.payment.domain.model.dto.APIResult;
import cn.monstar.payment.domain.model.dto.ApplyRefundResultDto;
import cn.monstar.payment.domain.model.dto.QueryRefundDto;
import cn.monstar.payment.domain.service.refund.RefundService;
import cn.monstar.payment.web.controller.form.ApplyRefundForm;
import cn.monstar.payment.web.controller.form.QueryRefundForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public APIResult sendRefunds(@RequestBody @Valid ApplyRefundForm applyRefundForm) {
        //业务逻辑处理
        ApplyRefundResultDto applyRefundResultDto = refundService.refundApplication(applyRefundForm);
        return APIResult.success().setData(applyRefundResultDto);
    }

    /**
     * 退款查询
     *
     * @param queryRefundForm 退款查询Form
     * @return
     */
    @PostMapping("/query")
    public APIResult queryRefund(@RequestBody @Valid QueryRefundForm queryRefundForm) {
        QueryRefundDto queryRefundDto = refundService.queryRefund(queryRefundForm);
        return APIResult.success().setData(queryRefundDto);
    }


}