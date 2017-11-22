package cn.monstar.payment.test.controller;

import cn.monstar.payment.config.TestsConfiguration;
import cn.monstar.payment.domain.model.dto.APIResultDto;
import cn.monstar.payment.web.controller.form.RefundsFrom;
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
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestsConfiguration.class)
public class RefundsControllerTest {

	private Logger logger = LoggerFactory.getLogger(RefundsControllerTest.class);

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void submitRefunds() {
		RefundsFrom refundsFrom = new RefundsFrom();
		refundsFrom.setOrderMoney("1");
		refundsFrom.setRefundMoney("0.5");
		refundsFrom.setPaymentNo("123456");
		//refundsFrom.setRefundDescription("退款原因");
		ResponseEntity<APIResultDto> responseEntity = testRestTemplate.postForEntity("/payment/refunds/sendRefund", refundsFrom, APIResultDto.class);
		Assert.assertEquals(responseEntity.getStatusCode().value(),200);
		logger.info("返回数据:{}", JSON.toJSON(responseEntity.getBody()));
	}

}