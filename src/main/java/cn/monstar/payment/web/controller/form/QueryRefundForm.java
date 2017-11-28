package cn.monstar.payment.web.controller.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author wangxianding
 * @version 1.0
 * @description 退款查询Form
 * @date 2017/11/28 下午4:07
 */
public class QueryRefundForm implements Serializable {

    private static final long serialVersionUID = -5370144288536070339L;
    /**
     * 平台交易流水号
     */
    @NotNull
    @Length(min = 32, max = 32)
    private String paymentNo;

    /**
     * 退款流水号
     */
    @NotNull
    @Length(min = 32, max = 32)
    private String refundNo;

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }
}
