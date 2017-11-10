/*
 * System Name    : XXXXX
 * SubSystem Name : Product
 * Class Name     : TestController
 * Description    : XXXXX
 */

package cn.monstar.payment.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Revision ：
 * Rev -- Date---------Name------------Note
 * 1.0    2017.11.10   MONSTAR-LAB-CN  Created
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
