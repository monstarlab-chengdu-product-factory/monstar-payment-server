package cn.monstar.payment.domain.util.wechat;

import java.io.Serializable;

/**
 * @author wangxianding
 * @version 1.0
 * @description 代金券
 * @date 2017/12/5 下午2:00
 */
public class Coupon implements Serializable {

    private static final long serialVersionUID = 4755657085644991813L;

    /**
     * 代金券类型
     */
    private String couponType;

    /**
     * 代金券id
     */
    private String couponId;

    /**
     * 单个代金券支付金额
     */
    private Integer couponFee;

    public Coupon() {
    }

    public Coupon(String couponType, String couponId, Integer couponFee) {
        this.couponType = couponType;
        this.couponId = couponId;
        this.couponFee = couponFee;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public Integer getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(Integer couponFee) {
        this.couponFee = couponFee;
    }
}
