package cn.monstar.payment.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhangshuai
 * @version 1.0
 * @description BaseController
 * @date 2017/11/22 16:09
 */
@Controller
public class TestController extends BaseController {

    @RequestMapping("/test1")
    @ResponseBody
    public String toTest() {
        logger.info("test");
        return "test";
    }

    @RequestMapping("/test2")
    public String toTest2() {
        logger.info("test2");
        return "test";
    }
}
