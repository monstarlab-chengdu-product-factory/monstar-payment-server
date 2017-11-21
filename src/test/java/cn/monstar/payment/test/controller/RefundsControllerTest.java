package cn.monstar.payment.test.controller;


import cn.monstar.payment.config.TestsConfiguration;
import cn.monstar.payment.domain.model.dto.APIResultDto;
import cn.monstar.payment.web.controller.form.RefundsFrom;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestsConfiguration.class)
public class RefundsControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void submitRefunds() {
		RefundsFrom refundsFrom = new RefundsFrom();
		refundsFrom.setOrderMoney("1");
		refundsFrom.setRefundMoney("0.5");
		refundsFrom.setPaymentNo("123456");
		refundsFrom.setRefundNo("666666");
		refundsFrom.setRefundDescription("退款原因");

		APIResultDto apiResultDto = testRestTemplate.postForObject("/payment/refunds/sendRefund", refundsFrom, APIResultDto.class);
		Assert.assertEquals(apiResultDto.getReturnCode(), 0);
		Assert.assertNotNull(apiResultDto.getData());
		Assert.assertNotNull(apiResultDto.getData());
	}

}