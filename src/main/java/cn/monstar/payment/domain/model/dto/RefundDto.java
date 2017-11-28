package cn.monstar.payment.domain.model.dto;

import cn.monstar.payment.domain.model.mybatis.gen.TRefund;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wangxianding
 * @version 1.0
 * @description 退款Dto
 * @date 2017/11/16 下午6:17
 */
public class RefundDto extends TRefund implements Serializable {

    private String paymentNo;


    public RefundDto() {
        super();
    }

    public RefundDto(BigDecimal orderMoney, BigDecimal refundMoney, String refundDescription, String paymentNo) {
        super();
        setOrderMoney(orderMoney);
        setRefundMoney(refundMoney);
        setRefundDescription(refundDescription);
        setPaymentNo(paymentNo);
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    @JsonIgnore
    @Override
    public Long getRefundId() {
        return super.getRefundId();
    }

    @JsonIgnore
    @Override
    public Long getPaymentId() {
        return super.getPaymentId();
    }
}
