/*
 * System Name    : XXXXX
 * SubSystem Name : Product
 * Class Name     : demoTest
 * Description    : XXXXX
 */

package cn.monstar.payment.test.controller;

import cn.monstar.payment.config.TestsConfiguration;
import cn.monstar.payment.domain.model.dto.APIResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Revision ：
 * Rev -- Date---------Name------------Note
 * 1.0    2017.11.10   MONSTAR-LAB-CN  Created
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestsConfiguration.class)
public class demoControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testDemo() {
        APIResult rs = this.restTemplate.getForObject("/order", APIResult.class);
        Assert.assertEquals(rs.getStatus(), 0);
    }
}
