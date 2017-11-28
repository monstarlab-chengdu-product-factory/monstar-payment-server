package cn.monstar.payment.web.controller.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author wangxianding
 * @version 1.0
 * @description 退款请求Form
 * @date 2017/11/21 上午9:50
 */
public class ApplyRefundForm implements Serializable {

    private static final long serialVersionUID = -437615029407074561L;
    /**
     * 平台交易流水号
     */
    @NotNull
    @Length(min = 32, max = 32)
    private String paymentNo;

    /**
     * 支付总金额
     */
    @NotNull
    @Pattern(regexp = "/^[0-9]+(\\.[0-9]{1,2})?$/")
    @Length(max = 15)
    private String orderMoney;

    /**
     * 退款金额
     */
    @NotNull
    @Pattern(regexp = "/^[0-9]+(\\.[0-9]{1,2})?$/")
    @Length(max = 15)
    private String refundMoney;

    /**
     * 退款原因
     */
    @NotNull
    @Length(max = 100)
    private String refundDescription;


    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(String refundMoney) {
        this.refundMoney = refundMoney;
    }

    public String getRefundDescription() {
        return refundDescription;
    }

    public void setRefundDescription(String refundDescription) {
        this.refundDescription = refundDescription;
    }
}