package cn.monstar.payment.web.controller;

import cn.monstar.payment.domain.model.dto.APIResult;
import cn.monstar.payment.domain.model.mybatis.gen.TPayment;
import cn.monstar.payment.domain.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zhangshuai
 * @version 1.0
 * @description BaseController
 * @date 2017/11/22 16:09
 */
@Controller("/order")
public class DemoController extends BaseController {

    @Autowired
    private PaymentService paymentService;

    @PutMapping("/{dad}")
    @ResponseBody
    public APIResult toTest() {
        logger.info("test");
        logger.info("dsdas");
        logger.info("dsadasda");
        return APIResult.success().setData("2").setMessage("dsdsdsf");
    }

    @DeleteMapping("/{dd}")
    @ResponseBody
    public APIResult toTest3() {
        return APIResult.success().setMessage("dsadada").setData(new Object());
    }

    @GetMapping
    @ResponseBody
    public APIResult toTest4() {
        TPayment payment = paymentService.findOne(1L);
        logger.debug("debug test");
        return APIResult.success();
    }


    @RequestMapping("/test2")
    public String toTest2() {
        logger.info("test2");
        return "test";
    }
}
