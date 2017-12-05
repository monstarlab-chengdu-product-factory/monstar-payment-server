package cn.monstar.payment.domain.util.wechat.response;

import cn.monstar.payment.domain.util.wechat.Coupon;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxianding
 * @version 1.0
 * @description
 * @date 2017/12/5 下午1:18
 */
@XStreamAlias("xml")
public class WxPayOrderQueryResponse extends AbstractWxPayBaseResponse implements Serializable {

    private static final long serialVersionUID = -4409572905143862539L;

    /**
     * 设备号
     * 微信支付分配的终端设备号
     * String(32)
     * 是否必填: 否
     */
    @XStreamAlias("device_info")
    private String deviceInfo;

    /**
     * 用户标识
     * 用户在商户appid下的唯一标识
     * String(128)
     * 是否必填: 是
     */
    @XStreamAlias("openid")
    private String openid;

    /**
     * 是否关注公众账号
     * 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
     * String(1)
     * 是否必填: 否
     */
    @XStreamAlias("is_subscribe")
    private String isSubscribe;

    /**
     * 交易类型
     * 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY
     * String(16)
     * 是否必填：是
     */
    @XStreamAlias("trade_type")
    private String tradeType;

    /**
     * 交易状态
     * SUCCESS—支付成功
     * REFUND—转入退款
     * NOTPAY—未支付
     * CLOSED—已关闭
     * REVOKED—已撤销（刷卡支付）
     * USERPAYING--用户支付中
     * PAYERROR--支付失败(其他原因，如银行返回失败)
     * String(32)
     * 是否必填: 是
     */
    @XStreamAlias("trade_state")
    private String tradeState;

    /**
     * 付款银行
     * 银行类型，采用字符串类型的银行标识
     * String(16)
     * 是否必填: 是
     */
    @XStreamAlias("bank_type")
    private String bankType;

    /**
     * 标价金额
     * 订单总金额，单位为分
     * int
     * 是否必填: 是
     */
    @XStreamAlias("total_fee")
    private Integer totalFee;

    /**
     * 应结订单金额
     * 当订单使用了免充值型优惠券后返回该参数，应结订单金额=订单金额-免充值优惠券金额。
     * int
     * 是否必填: 否
     */
    @XStreamAlias("settlement_total_fee")
    private Integer settlementTotalFee;

    /**
     * 标价币种
     * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY,详情见https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=4_2
     * String(8)
     * 是否必填: 否
     */
    @XStreamAlias("fee_type")
    private String feeType;

    /**
     * 现金支付金额
     * 现金支付金额订单现金支付金额，详见https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=4_2
     * int
     * 是否必填：是
     */
    @XStreamAlias("cash_fee")
    private Integer cashFee;

    /**
     * 现金支付币种
     * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=4_2
     * String(16)
     * 是否必填: 否
     */
    @XStreamAlias("cash_fee_type")
    private String cashFeeType;

    /**
     * 代金券金额
     * “代金券”金额<=订单金额，订单金额-“代金券”金额=现金支付金额，详见支付金额https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=4_2
     * int
     * 是否必填： 否
     */
    @XStreamAlias("coupon_fee")
    private Integer couponFee;

    /**
     * 代金券使用数量
     * int
     * 是否必填： 否
     */
    @XStreamAlias("coupon_count")
    private Integer couponCount;

    /**
     * 微信支付订单号
     * String(32)
     * 是否必填：是
     */
    @XStreamAlias("transaction_id")
    private String transactionId;

    /**
     * 商户订单号
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     * String(128)
     * 是否必填：是
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    /**
     * 附加数据
     * String(128)
     * 是否必填：否
     */
    @XStreamAlias("attach")
    private String attach;

    /**
     * 支付完成时间
     * 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=4_2
     * String(14)
     * 是否必填: 是
     */
    @XStreamAlias("time_end")
    private String timeEnd;

    /**
     * 交易状态描述
     * 对当前查询订单状态的描述和下一步操作的指引
     * String(256)
     * 是否必填: 是
     */
    @XStreamAlias("trade_state_desc")
    private String tradeStateDesc;

    private List<Coupon> coupons;

    /**
     * 通过xml组装coupons属性内容
     */
    public void composeCoupons() {
        if (this.couponCount != null && this.couponCount > 0) {
            this.coupons = new ArrayList<>();
            for (int i = 0; i < this.couponCount; i++) {
                this.coupons.add(new Coupon(this.getXmlValue("xml/coupon_type_" + i),
                        this.getXmlValue("xml/coupon_id_" + i),
                        this.getXmlValueAsInt("xml/coupon_fee_" + i)));
            }
        }
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getSettlementTotalFee() {
        return settlementTotalFee;
    }

    public void setSettlementTotalFee(Integer settlementTotalFee) {
        this.settlementTotalFee = settlementTotalFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Integer getCashFee() {
        return cashFee;
    }

    public void setCashFee(Integer cashFee) {
        this.cashFee = cashFee;
    }

    public String getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType;
    }

    public Integer getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(Integer couponFee) {
        this.couponFee = couponFee;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getTradeStateDesc() {
        return tradeStateDesc;
    }

    public void setTradeStateDesc(String tradeStateDesc) {
        this.tradeStateDesc = tradeStateDesc;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
}
