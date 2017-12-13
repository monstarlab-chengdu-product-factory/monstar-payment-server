package cn.monstar.payment.web.controller;

import cn.monstar.payment.domain.model.dto.APIResult;
import cn.monstar.payment.domain.model.dto.PayDto;
import cn.monstar.payment.domain.model.enums.AccessTypeEnum;
import cn.monstar.payment.domain.service.payment.PaymentService;
import cn.monstar.payment.web.controller.form.PayForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 手机H5支付
     *
     * @param payForm
     * @return
     */
    @PostMapping("/wapPay")
    public APIResult wapPay(@RequestBody PayForm payForm) {
        PayDto payDto = paymentService.createPayment(payForm, AccessTypeEnum.H5);
        return APIResult.success().setData(payDto);
    }

    /**
     * PC网站支付
     *
     * @param payForm
     * @return
     */
    @PostMapping("/pagePay")
    public APIResult pagePay(@RequestBody PayForm payForm) {
        PayDto payDto = paymentService.createPayment(payForm, AccessTypeEnum.QRCODE);
        return APIResult.success().setData(payDto);
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
