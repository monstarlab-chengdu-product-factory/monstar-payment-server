package cn.monstar.payment.domain.util.wechat.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author wangxianding
 * @version 1.0
 * @description 关闭订单结果
 * 详情请参考https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_3
 * @date 2017/12/5 下午5:00
 */
@XStreamAlias("xml")
public class WxPayCloseOrderResponse extends AbstractWxPayBaseResponse {

    /**
     * 业务结果描述
     * 对于业务执行的详细描述
     * String(32)
     * 是否必填: 是
     */
    @XStreamAlias("result_msg")
    private String resultMsg;

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
