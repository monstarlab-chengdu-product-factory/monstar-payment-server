package cn.monstar.payment.web.controller;

import cn.monstar.payment.domain.model.dto.APIResult;
import cn.monstar.payment.domain.model.dto.PayDto;
import cn.monstar.payment.domain.model.enums.AccessTypeEnum;
import cn.monstar.payment.domain.service.payment.PaymentService;
import cn.monstar.payment.domain.util.UserAgentUtil;
import cn.monstar.payment.web.controller.form.PayForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author liuyiqian
 * @version 1.0
 * @description
 * @date 2017/12/8 下午1:30
 */
@RestController
@RequestMapping("/payment")
public class PaymentsController extends BaseController {

    @Autowired
    private PaymentService paymentService;

    /**
     * 创建付款单
     *
     * @param payForm
     * @return
     */
    @PostMapping("/create")
    public APIResult create(@RequestBody @Valid PayForm payForm) {
        PayDto payDto = paymentService.createPayment(payForm);
        return APIResult.success().setData(payDto);
    }

    /**
     * 付款请求
     *
     * @param paymentNo
     * @param request
     * @return
     */
    @PostMapping("/pay")
    public String pay(@RequestParam(value = "paymentNo") String paymentNo, HttpServletRequest request) {
        String ua = request.getHeader("User-Agent");
        if(UserAgentUtil.checkAgentIsMobile(ua)) {
            return paymentService.payRequest(paymentNo, AccessTypeEnum.H5);
        } else {
            return paymentService.payRequest(paymentNo, AccessTypeEnum.QRCODE);
        }
    }

    /**
     * 支付状态查询
     *
     * @param paymentNo
     * @return
     */
    @PostMapping("/query")
    public APIResult query(@RequestParam(value = "paymentNo") String paymentNo) {
        return APIResult.success().setData(paymentService.paymentQuery(paymentNo));
    }
}
