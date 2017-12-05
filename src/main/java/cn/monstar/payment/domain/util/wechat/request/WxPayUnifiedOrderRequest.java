package cn.monstar.payment.domain.util.wechat.request;

import cn.monstar.payment.config.WxConfig;
import cn.monstar.payment.domain.model.enums.ExceptionEnum;
import cn.monstar.payment.domain.util.StringUtil;
import cn.monstar.payment.domain.util.constant.ConstantUtil;
import cn.monstar.payment.domain.util.wechat.annotation.Required;
import cn.monstar.payment.web.exception.wx.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.springframework.util.StringUtils;

/**
 * @author wangxianding
 * @version 1.0
 * @description 统一下单
 * @date 2017/11/29 上午10:44
 */
@XStreamAlias("xml")
public class WxPayUnifiedOrderRequest extends WxPayBaseRequest {

    /**
     * 设备号
     * 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
     * String(32)
     * 是否必填: 否
     */
    @XStreamAlias("device_info")
    private String deviceInfo;

    /**
     * 商品描述
     * 商品简单描述，该字段须严格按照规范传递，具体请见参数规定https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=4_2
     * String(128)
     * 是否必填: 是
     */
    @Required
    @XStreamAlias("body")
    private String body;

    /**
     * 商品详情
     * 单品优惠字段(暂未上线)
     * String(6000)
     * 是否必填：否
     */
    @XStreamAlias("detail")
    private String detail;

    /**
     * 附加数据
     * 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
     * String(127)
     * 是否必填: 否
     */
    @XStreamAlias("attach")
    private String attach;

    /**
     * 商户订单号
     * 商户系统内部的订单号,32个字符内、可包含字母,
     * 其他说明见https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=4_2
     * String(32)
     * 是否必填: 是
     */
    @Required
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    /**
     * 货币类型
     * 符合ISO 4217标准的三位字母代码，默认人民币：CNY，
     * 其他值列表详见https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=4_2
     * String(16)
     * 是否必填否
     */
    @XStreamAlias("fee_type")
    private String feeType;

    /**
     * 总金额
     * 订单总金额，单位为分
     * int
     * 是否必填，是
     */
    @Required
    @XStreamAlias("total_fee")
    private int totalFee;

    /**
     * 终端IP
     * 必须传正确的用户端IP
     * String(16)
     * 是否必填：是
     */
    @Required
    @XStreamAlias("spbill_create_ip")
    private String spbillCreateIp;

    /**
     * 交易起始时间
     * 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。
     * 其他详见https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=4_2
     * String(14)
     * 是否必填: 否
     */
    @XStreamAlias("time_start")
    private String timeStart;

    /**
     * 交易结束时间
     * 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。
     * 其他详见https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=4_2
     * 注意：最短失效时间间隔必须大于5分钟
     * String(14)
     * 是否必填：否
     */
    @XStreamAlias("time_expire")
    private String timeExpire;

    /**
     * 商品标记
     * 商品标记，代金券或立减优惠功能的参数，
     * 说明详见https://pay.weixin.qq.com/wiki/doc/api/tools/sp_coupon.php?chapter=12_1
     * String(32)
     * 是否必填：否
     */
    @XStreamAlias("goods_tag")
    private String goodsTag;

    /**
     * 通知地址:
     * 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数
     * String(256)
     * 是否必填: 是
     */
    @Required
    @XStreamAlias("notify_url")
    private String notifyUrl;

    /**
     * 交易类型：
     * H5支付的交易类型为MWEB
     * String(16)
     * 是否必填: 是
     */
    @Required
    @XStreamAlias("trade_type")
    private String tradeType;

    /**
     * 商品ID
     * trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
     * String(32)
     * 是否必填: 否
     */
    @XStreamAlias("product_id")
    private String productId;

    /**
     * 指定支付方式
     * no_credit--指定不能使用信用卡支付
     * String(32)
     * 是否必填: 否
     */
    @XStreamAlias("limit_pay")
    private String limitPay;

    /**
     * 用户标识
     * trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。
     * openid如何获取，可参考【获取openid】。
     * 企业号请使用【企业号OAuth2.0接口】获取企业号内成员userid，再调用【企业号userid转openid接口】进行转换
     * String(128)
     */
    @XStreamAlias("openid")
    private String openid;

    /**
     * 场景信息
     * 该字段用于上报场景信息，目前支持上报实际门店信息。
     * 该字段为JSON对象数据，对象格式为
     * {"store_info":{"id": "门店ID","name": "名称","area_code": "编码","address": "地址" }}
     * String(256)
     * 是否必填: 该字段用于上报支付的场景信息,针对H5支付有以下三种场景,
     * 请根据对应场景上报,H5支付不建议在APP端使用，
     * 针对场景1，2请接入APP支付，不然可能会出现兼容性问题
     * 1，IOS移动应用
     * {
     * "h5_info": //h5支付固定传"h5_info"
     * {
     * "type": "",  //场景类型
     * "app_name": "",  //应用名
     * "bundle_id": ""  //bundle_id
     * }
     * }
     * <p>
     * 2，安卓移动应用
     * {
     * "h5_info": //h5支付固定传"h5_info"
     * {
     * "type": "",  //场景类型
     * "app_name": "",  //应用名
     * "package_name": ""  //包名
     * }
     * }
     * <p>
     * 3，WAP网站应用
     * {
     * "h5_info": //h5支付固定传"h5_info"
     * {
     * "type": "",  //场景类型
     * "wap_url": "",//WAP网站URL地址
     * "wap_name": ""  //WAP 网站名
     * }
     * }
     */
    @XStreamAlias("scene_info")
    private String sceneInfo;

    @Override
    public void checkedAndSign(WxConfig wxConfig) {
        if (StringUtils.isEmpty(wxConfig.getNotifyUrl())) {
            throw new RuntimeException("notifyUrl is not allowed to be empty");
        }
        super.checkedAndSign(wxConfig);
    }

    @Override
    protected void checkConstraints() {
        //检查交易类型
        switch (this.tradeType) {
            case ConstantUtil.TRADE_APP:
                break;
            /**
             * trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识
             */
            case ConstantUtil.TRADE_JSAPI:
                if (StringUtils.isEmpty(this.openid)) {
                    throw new WxPayException(String.format(ExceptionEnum.PARAMREQUIRED.getLabel(), "openid"));
                }
                break;
            /**
             * trade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
             */
            case ConstantUtil.TRADE_NATIVE:
                if (StringUtils.isEmpty(this.productId)) {
                    throw new WxPayException(String.format(ExceptionEnum.PARAMREQUIRED.getLabel(), "product_id"));
                }
                break;
            /**
             * 该字段用于上报支付的场景信息,针对H5支付有以下三种场景,请根据对应场景上报,H5支付不建议在APP端使用，针对场景1，2请接入APP支付，不然可能会出现兼容性问题
             */
            case ConstantUtil.TRADE_H5:
                if (StringUtils.isEmpty(this.sceneInfo)) {
                    throw new WxPayException(String.format(ExceptionEnum.PARAMREQUIRED.getLabel(), "scene_info"));
                }
                break;
        }
    }

    public WxPayUnifiedOrderRequest(){}

    public WxPayUnifiedOrderRequest(Builder builder){
        setAppid(builder.appid);
        setMchId(builder.mchId);
        setDeviceInfo(builder.deviceInfo);
        setNonceStr(builder.nonceStr);
        setNotifyUrl(builder.notifyUrl);
        setSign(builder.sign);
        setSignType(builder.signType);
        setBody(builder.body);
        setDetail(builder.detail);
        setAttach(builder.attach);
        setOutTradeNo(builder.outTradeNo);
        setFeeType(builder.feeType);
        setTotalFee(builder.totalFee);
        setSpbillCreateIp(builder.spbillCreateIp);
        setTimeStart(builder.timeStart);
        setTimeExpire(builder.timeExpire);
        setGoodsTag(builder.goodsTag);
        setTradeType(builder.tradeType);
        setProductId(builder.productId);
        setLimitPay(builder.limitPay);
        setOpenid(builder.openid);
        setSceneInfo(builder.sceneInfo);

    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(String sceneInfo) {
        this.sceneInfo = sceneInfo;
    }

    public static class Builder {

        private String appid;
        private String mchId;
        private String deviceInfo;
        private String nonceStr;
        private String sign;
        private String signType;
        private String body;
        private String detail;
        private String attach;
        private String outTradeNo;
        private String feeType;
        private Integer totalFee;
        private String spbillCreateIp;
        private String timeStart;
        private String timeExpire;
        private String goodsTag;
        private String notifyUrl;
        private String tradeType;
        private String productId;
        private String limitPay;
        private String openid;
        private String sceneInfo;

        public Builder() {
        }

        public Builder setAppid(String appid) {
            this.appid = appid;
            return this;
        }

        public Builder setMchId(String mchId) {
            this.mchId = mchId;
            return this;
        }

        public Builder setDeviceInfo(String deviceInfo) {
            this.deviceInfo = deviceInfo;
            return this;
        }

        public Builder setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
            return this;
        }

        public Builder setSign(String sign) {
            this.sign = sign;
            return this;
        }

        public Builder setSignType(String signType) {
            this.signType = signType;
            return this;
        }

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Builder setDetail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder setAttach(String attach) {
            this.attach = attach;
            return this;
        }

        public Builder setOutTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
            return this;
        }

        public Builder setFeeType(String feeType) {
            this.feeType = feeType;
            return this;
        }

        public Builder setTotalFee(Integer totalFee) {
            this.totalFee = totalFee;
            return this;
        }

        public Builder setSpbillCreateIp(String spbillCreateIp) {
            this.spbillCreateIp = spbillCreateIp;
            return this;
        }

        public Builder setTimeStart(String timeStart) {
            this.timeStart = timeStart;
            return this;
        }

        public Builder setTimeExpire(String timeExpire) {
            this.timeExpire = timeExpire;
            return this;
        }

        public Builder setGoodsTag(String goodsTag) {
            this.goodsTag = goodsTag;
            return this;
        }

        public Builder setNotifyUrl(String notifyUrl) {
            this.notifyUrl = notifyUrl;
            return this;
        }

        public Builder setTradeType(String tradeType) {
            this.tradeType = tradeType;
            return this;
        }

        public Builder setProductId(String productId) {
            this.productId = productId;
            return this;
        }

        public Builder setLimitPay(String limitPay) {
            this.limitPay = limitPay;
            return this;
        }

        public Builder setOpenid(String openid) {
            this.openid = openid;
            return this;
        }

        public Builder setSceneInfo(String sceneInfo) {
            this.sceneInfo = sceneInfo;
            return this;
        }

        public WxPayUnifiedOrderRequest newBuiler()

        {
            return new WxPayUnifiedOrderRequest(this);
        }

    }

}
