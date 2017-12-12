package cn.monstar.payment.domain.model.dto;

import java.io.Serializable;

/**
 * @author liuyiqian
 * @version 1.0
 * @description
 * @date 2017/12/8 下午5:57
 */
public class PayDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页面
     */
    private String htmlStr;

    /**
     * 平台交易流水号
     */
    private String paymentNo;

    public String getHtmlStr() {
        return htmlStr;
    }

    public void setHtmlStr(String htmlStr) {
        this.htmlStr = htmlStr;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }
}
