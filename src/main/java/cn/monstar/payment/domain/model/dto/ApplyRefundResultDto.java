package cn.monstar.payment.domain.model.dto;

import cn.monstar.payment.domain.model.enums.RefundStatusEnum;
import cn.monstar.payment.domain.model.mybatis.gen.TRefund;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wangxianding
 * @version 1.0
 * @description 退款Dto
 * @date 2017-11-28 16:46:35
 */
public class ApplyRefundResultDto extends TRefund implements Serializable {

    private String paymentNo;

    public ApplyRefundResultDto() {
        super();
    }

    public ApplyRefundResultDto(BigDecimal orderMoney, BigDecimal refundMoney, String refundDescription, String paymentNo) {
        super();
        setOrderMoney(orderMoney);
        setRefundMoney(refundMoney);
        setRefundDescription(refundDescription);
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

    @JsonIgnore
    @Override
    public Date getCreDt() {
        return super.getCreDt();
    }

    public int getRefundState() {
        return super.getRefundStatus() == null ? 0 : super.getRefundStatus().getEnumValue();
    }

    @JsonIgnore
    @Override
    public RefundStatusEnum getRefundStatus() {
        return super.getRefundStatus();
    }

    @JsonIgnore
    @Override
    public String getRefundDescription() {
        return super.getRefundDescription();
    }

    @JsonIgnore
    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }
}
