package cn.monstar.payment.domain.util;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author wangxianding
 * @version 1.0
 * @description
 * @date 2017/11/27 上午9:52
 */
public class StringUtilTest {

    @Test
    public void uuidGenerator() throws Exception {
        Assert.assertEquals(StringUtil.uuidGenerator().length(), 32);
    }

}