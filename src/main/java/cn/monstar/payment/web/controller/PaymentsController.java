package cn.monstar.payment.web.controller;

import cn.monstar.payment.config.ApplicationConfig;
import cn.monstar.payment.domain.model.dto.APIResult;
import cn.monstar.payment.domain.model.dto.PayDto;
import cn.monstar.payment.domain.model.enums.AccessTypeEnum;
import cn.monstar.payment.domain.service.payment.PaymentService;
import cn.monstar.payment.web.controller.form.PayForm;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private ApplicationConfig applicationConfig;

    /**
     * alipay notify
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/alipay/notify", method = {RequestMethod.GET, RequestMethod.POST})
    public String handleAlipayNotify(HttpServletRequest request) {
        logger.info("get alipay notify");
        Map<String, String> paramMap = getAllRequestParam(request);
        try {
            boolean verifyResult = AlipaySignature.rsaCheckV1(paramMap, applicationConfig.alipayPublicKey, AlipayConstants.CHARSET_UTF8, AlipayConstants.SIGN_TYPE_RSA2);
            if (verifyResult) {
                String appId = paramMap.get("app_id");
                String paymentNo = paramMap.get("out_trade_no");
                String outTradeNo = paramMap.get("trade_no");
                String orderMoney = paramMap.get("total_amount");
                if (applicationConfig.alipayId.equals(appId) && paymentService.paymentCorrectCheck(paymentNo, new BigDecimal(orderMoney))) {
                    paymentService.updateToFinish(paymentNo, outTradeNo);
                    return "success";
                }
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return "failure";
    }

    @PostMapping("/wapPay")
    public APIResult wapPay(@RequestBody PayForm payForm) {
        PayDto payDto = paymentService.createPayment(payForm, AccessTypeEnum.H5);
        return APIResult.success().setData(payDto);
    }

    @PostMapping("/pagePay")
    public APIResult pagePay(@RequestBody PayForm payForm) {
        PayDto payDto = paymentService.createPayment(payForm, AccessTypeEnum.QRCODE);
        return APIResult.success().setData(payDto);
    }

    @PostMapping("/query")
    public APIResult query(@RequestParam(value = "paymentNo") String paymentNo) {
        return APIResult.success().setData(paymentService.paymentQuery(paymentNo));
    }

    /**
     * get param
     *
     * @param request
     * @return
     */
    private Map<String, String> getAllRequestParam(HttpServletRequest request) {
        Map<String, String> res = new HashMap<>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
                if (null == res.get(en) || "".equals(res.get(en))) {
                    res.remove(en);
                }
            }
        }
        return res;
    }
}
