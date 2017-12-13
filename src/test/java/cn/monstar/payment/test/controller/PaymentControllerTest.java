package cn.monstar.payment.test.controller;

import cn.monstar.payment.config.TestsConfiguration;
import cn.monstar.payment.domain.model.dto.APIResult;
import cn.monstar.payment.domain.model.dto.PayDto;
import cn.monstar.payment.domain.model.enums.PaymentStatusEnum;
import cn.monstar.payment.domain.model.enums.PaymentTypeEnum;
import cn.monstar.payment.web.controller.form.PayForm;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuyiqian
 * @version 1.0
 * @description
 * @date 2017/12/11 下午2:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestsConfiguration.class)
public class PaymentControllerTest {

    private Logger logger = LoggerFactory.getLogger(PaymentControllerTest.class);

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void wapPayTest() {
        PayForm payForm = new PayForm();
        payForm.setOrderMoney(new BigDecimal(1));
        payForm.setPaymentType(PaymentTypeEnum.ALIPAY);
        payForm.setGoodsInfo("testgoods");
        payForm.setDescription("description");
        payForm.setGoodsDetails("detail");
        payForm.setUserNo("a12345");
        APIResult response = testRestTemplate.postForObject("/payment/wapPay", payForm, APIResult.class);
        Map<String, String> map = (Map)response.getData();

        String paymentNo = map.get("paymentNo");
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("paymentNo", paymentNo);
        APIResult response2 = testRestTemplate.postForObject("/payment/query", param, APIResult.class);
        Map<String, String> map2 = (Map)response2.getData();
        Assert.assertEquals(PaymentStatusEnum.UNPAID.name(), map2.get("paymentStatus"));
        Assert.assertEquals(payForm.getDescription(), map2.get("description"));
        Assert.assertEquals(payForm.getPaymentType().name(), map2.get("paymentType"));
        Assert.assertEquals(1.0, map2.get("orderMoney"));
        Assert.assertEquals(payForm.getGoodsInfo(), map2.get("goodsInfo"));
    }
}
