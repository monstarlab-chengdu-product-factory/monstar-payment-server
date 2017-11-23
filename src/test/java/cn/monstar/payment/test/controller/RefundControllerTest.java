package cn.monstar.payment.test.controller;

import cn.monstar.payment.config.TestsConfiguration;
import cn.monstar.payment.domain.model.dto.APIResultDto;
import cn.monstar.payment.web.controller.form.RefundForm;
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
		RefundForm refundForm = new RefundForm();
		refundForm.setOrderMoney("1");
		refundForm.setRefundMoney("0.5");
		refundForm.setPaymentNo("123456");
		refundForm.setRefundDescription("退款原因");
		logger.info("请求数据:{}", JSON.toJSON(refundForm));
		ResponseEntity<APIResultDto> responseEntity = testRestTemplate.postForEntity("/payment/refunds/sendRefund", refundForm, APIResultDto.class);
		Assert.assertEquals(responseEntity.getStatusCode().value(),200);
		logger.info("返回数据:{}", JSON.toJSON(responseEntity.getBody()));
		APIResultDto apiResultDto = responseEntity.getBody();
		Assert.assertNotNull(apiResultDto);
		Assert.assertEquals(apiResultDto.getReturnCode(), 0);
		LinkedHashMap<String, Object> objectLinkedHashMap = (LinkedHashMap<String, Object>) apiResultDto.getData();
		Assert.assertEquals(objectLinkedHashMap.get("orderMoney"), refundForm.getOrderMoney());
		Assert.assertNotNull(objectLinkedHashMap.get("refundNo"));
//		RefundForm refundsFrom1 = (RefundForm) apiResultDto.getData();
//		Assert.assertNotNull(refundsFrom1);
//		Assert.assertEquals(refundsFrom1.getOrderMoney(), refundsFrom.getOrderMoney());
//		Assert.assertEquals(refundsFrom1.getRefundMoney(), refundsFrom.getRefundMoney());
	}

}