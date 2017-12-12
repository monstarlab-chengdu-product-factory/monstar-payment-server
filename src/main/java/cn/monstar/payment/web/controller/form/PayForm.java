package cn.monstar.payment.web.controller.form;

import cn.monstar.payment.domain.model.enums.PaymentTypeEnum;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author liuyiqian
 * @version 1.0
 * @description
 * @date 2017/12/8 下午5:10
 */
public class PayForm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 支付金额
     */
    @NotNull
    private BigDecimal orderMoney;

    /**
     * 支付类型
     */
    @NotNull
    private PaymentTypeEnum paymentType;

    /**
     * 商品标题
     */
    @NotNull
    private String goodsInfo;

    /**
     * 操作员
     */
    private String userNo;

    /**
     * 附加数据
     */
    private String description;

    /**
     * 支付创建时间
     */
    private String orderCreDt;

    /**
     * 支付失效时间
     */
    private Integer expireTimes;

    /**
     * 商品描述
     */
    private String goodsDetails;

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    public PaymentTypeEnum getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentTypeEnum paymentType) {
        this.paymentType = paymentType;
    }

    public String getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrderCreDt() {
        return orderCreDt;
    }

    public void setOrderCreDt(String orderCreDt) {
        this.orderCreDt = orderCreDt;
    }

    public Integer getExpireTimes() {
        return expireTimes;
    }

    public void setExpireTimes(Integer expireTimes) {
        this.expireTimes = expireTimes;
    }

    public String getGoodsDetails() {
        return goodsDetails;
    }

    public void setGoodsDetails(String goodsDetails) {
        this.goodsDetails = goodsDetails;
    }
}
