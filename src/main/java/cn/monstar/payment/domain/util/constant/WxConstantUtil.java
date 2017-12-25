package cn.monstar.payment.domain.util.constant;

/**
 * @author wangxianding
 * @version 1.0
 * @description 微信常量工具类
 * @date 2017/12/1 上午10:28
 */
public interface WxConstantUtil {

    /**
     * 微信交易类型 JSAPI
     */
    String TRADE_JSAPI = "JSAPI";

    /**
     * 微信交易类型 NATIVE
     */
    String TRADE_NATIVE = "NATIVE";

    /**
     * 微信交易类型 APP
     */
    String TRADE_APP = "APP";

    /**
     * 微信交易类型 H5
     */
    String TRADE_H5 = "MWEB";

    /**
     * 微信交易状态 SUCCESS 支付成功
     */
    String TRADE_STATE_SUCCESS = "SUCCESS";

    /**
     * 微信交易状态 REFUND 转入退款
     */
    String TRADE_STATE_REFUND = "REFUND";

    /**
     * 微信交易状态 NOTPAY 未支付
     */
    String TRADE_STATE_NOTPAY = "NOTPAY";

    /**
     * 微信交易状态 CLOSED 已关闭
     */
    String TRADE_STATE_CLOSED = "CLOSED";

    /**
     * 微信交易状态 REVOKED 已撤销（刷卡支付）
     */
    String TRADE_STATE_REVOKED = "REVOKED";

    /**
     * 微信交易状态 USERPAYING 用户支付中
     */
    String TRADE_STATE_USERPAYING = "USERPAYING";

    /**
     * 微信交易状态 PAYERROR 支付失败(其他原因，如银行返回失败)
     */
    String TRADE_STATE_PAYERROR = "PAYERROR";

}
