package cn.monstar.payment.test.controller;

import cn.monstar.payment.config.TestsConfiguration;
import cn.monstar.payment.domain.model.dto.APIResult;
import cn.monstar.payment.web.controller.form.ApplyRefundForm;
import cn.monstar.payment.web.controller.form.QueryRefundForm;
import com.alibaba.fastjson.JSON;
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

import java.util.LinkedHashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestsConfiguration.class)
public class RefundControllerTest {

    private Logger logger = LoggerFactory.getLogger(RefundControllerTest.class);

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void submitRefunds() {
        ApplyRefundForm applyRefundForm = new ApplyRefundForm();
        applyRefundForm.setOrderMoney("1");
        applyRefundForm.setRefundMoney("0.5");
        applyRefundForm.setPaymentNo("12345678901234567890123456789012");
        applyRefundForm.setRefundDescription("退款原因");
        logger.info("请求数据:{}", JSON.toJSON(applyRefundForm));

        String uri = "/payment/refunds/sendRefund";
        ResponseEntity<APIResult> responseEntity = testRestTemplate.postForEntity(uri, applyRefundForm, APIResult.class);

        Assert.assertEquals(responseEntity.getStatusCode().value(), 200);
        logger.info("返回数据:{}", JSON.toJSON(responseEntity.getBody()));
        APIResult apiResult = responseEntity.getBody();
        Assert.assertNotNull(apiResult);

        if (apiResult.getStatus() == 0) {
            LinkedHashMap<String, Object> objectLinkedHashMap = (LinkedHashMap<String, Object>) apiResult.getData();
            Assert.assertEquals(objectLinkedHashMap.get("orderMoney"), applyRefundForm.getOrderMoney());
            Assert.assertNotNull(objectLinkedHashMap.get("refundNo"));
        } else {
            logger.info("state:{},message:{}", apiResult.getStatus(), apiResult.getMessage());
        }
    }

    @Test
    public void queryRefund() {
        QueryRefundForm queryRefundForm = new QueryRefundForm();
        queryRefundForm.setPaymentNo("12345678901234567890123456789012");
        queryRefundForm.setRefundNo("12345678901234567890123456789012");

        String uri = "/payment/refunds/query";
        ResponseEntity<APIResult> responseEntity = testRestTemplate.postForEntity(uri, queryRefundForm, APIResult.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertNotNull(responseEntity.getBody());

        APIResult apiResult = responseEntity.getBody();
        if (apiResult.getStatus() == 0) {
            LinkedHashMap<String, Object> objectLinkedHashMap = (LinkedHashMap<String, Object>) apiResult.getData();
            Assert.assertNotNull(objectLinkedHashMap.get("outRefundNo"));
            Assert.assertNotNull(objectLinkedHashMap.get("refundStatus"));
        } else {
            logger.info("state:{},message:{}", apiResult.getStatus(), apiResult.getMessage());
        }

    }

}