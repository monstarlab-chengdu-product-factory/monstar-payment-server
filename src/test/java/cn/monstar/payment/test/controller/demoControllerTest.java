/*
 * System Name    : XXXXX
 * SubSystem Name : Product
 * Class Name     : demoTest
 * Description    : XXXXX
 */

package cn.monstar.payment.test.controller;

import cn.monstar.payment.config.TestsConfiguration;
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
        String rs = this.restTemplate.getForObject("/test1", String.class);
        Assert.assertEquals(rs, "test");
    }
}
