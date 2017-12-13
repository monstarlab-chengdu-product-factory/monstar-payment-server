package cn.monstar.payment.domain.model.dto;

import cn.monstar.payment.domain.model.enums.PaymentStatusEnum;
import cn.monstar.payment.domain.model.enums.PaymentTypeEnum;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author liuyiqian
 * @version 1.0
 * @description
 * @date 2017/12/11 上午10:04
 */
public class PayQueryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平台交易流水号
     */
    private String paymentNo;

    /**
     * 订单状态
     */
    private PaymentStatusEnum paymentStatus;

    /**
     * 附加数据
     */
    private String description;

    /**
     * 支付类型
     */
    private PaymentTypeEnum paymentType;

    /**
     * 支付金额
     */
    private BigDecimal orderMoney;

    /**
     * 商品标题
     */
    private String goodsInfo;

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public PaymentStatusEnum getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatusEnum paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PaymentTypeEnum getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentTypeEnum paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo) {
        this.goodsInfo = goodsInfo;
    }
}
