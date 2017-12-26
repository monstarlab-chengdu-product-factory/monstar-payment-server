package cn.monstar.payment.web.controller;

import cn.monstar.payment.domain.model.enums.AccessTypeEnum;
import cn.monstar.payment.domain.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuyiqian
 * @version 1.0
 * @description
 * @date 2017/12/26 下午5:29
 */
@RestController
@RequestMapping("/internal")
public class InternalController extends BaseController {

    @Autowired
    private PaymentService paymentService;

    /**
     * 付款请求
     *
     * @param paymentNo
     * @param accessType
     * @return
     */
    @PostMapping("/pay")
    public String pay(@RequestParam(value = "paymentNo") String paymentNo, @RequestParam(value = "accessType") Integer accessType) {
        if(accessType == AccessTypeEnum.H5.getEnumValue()) {
            return paymentService.payRequest(paymentNo, AccessTypeEnum.H5);
        } else {
            return paymentService.payRequest(paymentNo, AccessTypeEnum.QRCODE);
        }
    }
}
