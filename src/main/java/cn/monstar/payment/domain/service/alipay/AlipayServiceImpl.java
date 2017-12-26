package cn.monstar.payment.domain.service.alipay;

import cn.monstar.payment.config.AlipayConfig;
import cn.monstar.payment.domain.dao.mybatis.TPaymentMapper;
import cn.monstar.payment.domain.dao.mybatis.TRefundMapper;
import cn.monstar.payment.domain.model.enums.PaymentStatusEnum;
import cn.monstar.payment.domain.model.enums.RefundStatusEnum;
import cn.monstar.payment.domain.model.mybatis.gen.TPayment;
import cn.monstar.payment.domain.model.mybatis.gen.TRefund;
import cn.monstar.payment.web.exception.BusinessException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liuyiqian
 * @version 1.0
 * @description
 * @date 2017/12/5 下午4:21
 */
@Service
public class AlipayServiceImpl implements AlipayService {

    private Logger logger = LoggerFactory.getLogger(AlipayServiceImpl.class);

    private static final String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";

    private static final String TRADE_CLOSED = "TRADE_CLOSED";

    private static final String TRADE_SUCCESS = "TRADE_SUCCESS";

    private static final String TRADE_FINISHED = "TRADE_FINISHED";

    @Autowired
    private TPaymentMapper tPaymentMapper;

    @Autowired
    private TRefundMapper tRefundMapper;

    @Autowired
    private AlipayConfig alipayConfig;

    @Override
    public String tradeWapPay(String paymentNo) throws Exception {

        logger.info("create wap pay of alipay request. paymentNo is {}", paymentNo);

        TPayment tPayment = tPaymentMapper.findByPaymentNo(paymentNo);

        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.serverUrl, alipayConfig.alipayId,
                alipayConfig.privateKey,
                AlipayConstants.FORMAT_JSON, AlipayConstants.CHARSET_UTF8,
                alipayConfig.publicKey, AlipayConstants.SIGN_TYPE_RSA2);
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
        alipayRequest.setNotifyUrl(alipayConfig.notifyUrl);
        alipayRequest.setReturnUrl(alipayConfig.returnUrl);
        StringBuilder builder = new StringBuilder();
        builder = builder.append("{\"out_trade_no\":\"")
                .append(tPayment.getPaymentNo()).append("\",")
                .append("\"total_amount\":").append(tPayment.getOrderMoney()).append(",")
                .append("\"subject\":\"").append(tPayment.getGoodsInfo()).append("\",")
                .append("\"timeout_express\":\"").append(tPayment.getExpireTimes()).append("m\",")
                .append("\"product_code\":\"QUICK_WAP_PAY\"")
                .append("}");
        alipayRequest.setBizContent(builder.toString());
        return alipayClient.pageExecute(alipayRequest).getBody();
    }

    @Override
    public String tradePagePay(String paymentNo) throws Exception {

        logger.info("create page pay of alipay request. paymentNo is {}", paymentNo);

        TPayment tPayment = tPaymentMapper.findByPaymentNo(paymentNo);

        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.serverUrl, alipayConfig.alipayId,
                alipayConfig.privateKey,
                AlipayConstants.FORMAT_JSON, AlipayConstants.CHARSET_UTF8,
                alipayConfig.publicKey, AlipayConstants.SIGN_TYPE_RSA2);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setNotifyUrl(alipayConfig.notifyUrl);
        alipayRequest.setReturnUrl(alipayConfig.returnUrl);
        StringBuilder builder = new StringBuilder();
        builder = builder.append("{\"out_trade_no\":\"")
                .append(tPayment.getPaymentNo()).append("\",")
                .append("\"total_amount\":").append(tPayment.getOrderMoney()).append(",")
                .append("\"subject\":\"").append(tPayment.getGoodsInfo()).append("\",")
                .append("\"timeout_express\":\"").append(tPayment.getExpireTimes()).append("m\",")
                .append("\"product_code\":\"FAST_INSTANT_TRADE_PAY\"")
                .append("}");
        alipayRequest.setBizContent(builder.toString());
        return alipayClient.pageExecute(alipayRequest).getBody();
    }

    @Override
    public TPayment tradeQuery(String paymentNo) {

        logger.info("create trade query of alipay request. paymentNo is {}", paymentNo);

        TPayment tPayment = tPaymentMapper.findByPaymentNo(paymentNo);

        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.serverUrl, alipayConfig.alipayId,
                alipayConfig.privateKey,
                AlipayConstants.FORMAT_JSON, AlipayConstants.CHARSET_UTF8,
                alipayConfig.publicKey, AlipayConstants.SIGN_TYPE_RSA2);
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
        StringBuilder builder = new StringBuilder();
        builder = builder.append("{\"out_trade_no\":\"")
                .append(paymentNo).append("\"")
                .append("}");
        alipayRequest.setBizContent(builder.toString());
        AlipayTradeQueryResponse alipayResponse = new AlipayTradeQueryResponse();
        try {
            alipayResponse = alipayClient.execute(alipayRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (alipayResponse.isSuccess()) {
            switch (alipayResponse.getTradeStatus()) {
                case WAIT_BUYER_PAY:
                    tPayment.setPaymentStatus(PaymentStatusEnum.UNPAID);
                case TRADE_CLOSED:
                    tPayment.setPaymentStatus(PaymentStatusEnum.CLOSED);
                case TRADE_SUCCESS:
                case TRADE_FINISHED:
                    tPayment.setPaymentStatus(PaymentStatusEnum.PAID);
                    tPayment.setOutTradeNo(alipayResponse.getTradeNo());
            }
            TPayment tPaymentUpdate = new TPayment();
            tPaymentUpdate.setPaymentId(tPayment.getPaymentId());
            tPaymentUpdate.setPaymentStatus(tPayment.getPaymentStatus());
            tPaymentUpdate.setOutTradeNo(tPayment.getOutTradeNo());
            tPaymentMapper.updateByPrimaryKeySelective(tPaymentUpdate);
        }
        return tPayment;
    }

    @Override
    public boolean tradeRefund(String refundNo) {

        logger.info("create refund of alipay request. refundNo is {}", refundNo);

        TRefund tRefund = tRefundMapper.findByRefundNo(refundNo);
        TPayment tPayment = tPaymentMapper.selectByPrimaryKey(tRefund.getPaymentId());

        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.serverUrl, alipayConfig.alipayId,
                alipayConfig.privateKey,
                AlipayConstants.FORMAT_JSON, AlipayConstants.CHARSET_UTF8,
                alipayConfig.publicKey, AlipayConstants.SIGN_TYPE_RSA2);
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        StringBuilder builder = new StringBuilder();
        builder = builder.append("{\"out_trade_no\":\"")
                .append(tPayment.getPaymentNo()).append("\",")
                .append("\"trade_no\":\"").append(tPayment.getOutTradeNo()).append("\",")
                .append("\"refund_amount\":").append(tRefund.getRefundMoney()).append(",")
                .append("\"out_request_no\":\"").append(tRefund.getRefundNo()).append("\"")
                .append("}");
        alipayRequest.setBizContent(builder.toString());
        AlipayTradeRefundResponse alipayResponse = new AlipayTradeRefundResponse();
        try {
            alipayResponse = alipayClient.execute(alipayRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (alipayResponse.isSuccess()) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public TRefund refundQuery(String refundNo) {

        logger.info("create refund query of alipay request. refundNo is {}", refundNo);

        TRefund tRefund = tRefundMapper.findByRefundNo(refundNo);
        TPayment tPayment = tPaymentMapper.selectByPrimaryKey(tRefund.getPaymentId());

        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.serverUrl, alipayConfig.alipayId,
                alipayConfig.privateKey,
                AlipayConstants.FORMAT_JSON, AlipayConstants.CHARSET_UTF8,
                alipayConfig.publicKey, AlipayConstants.SIGN_TYPE_RSA2);
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();
        StringBuilder builder = new StringBuilder();
        builder = builder.append("{\"trade_no\":\"")
                .append(tPayment.getOutTradeNo()).append("\",")
                .append("\"out_trade_no\":\"").append(tPayment.getPaymentNo()).append("\",")
                .append("\"out_request_no\":\"").append(tRefund.getRefundNo()).append("\"")
                .append("}");
        alipayRequest.setBizContent(builder.toString());
        AlipayTradeFastpayRefundQueryResponse alipayResponse = new AlipayTradeFastpayRefundQueryResponse();
        try {
            alipayResponse = alipayClient.execute(alipayRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (alipayResponse.isSuccess()) {
            if (tRefund.getRefundStatus() != RefundStatusEnum.SUCCESSFULREFUND) {
                tRefund.setRefundStatus(RefundStatusEnum.SUCCESSFULREFUND);
                TRefund tRefundUpdate = new TRefund();
                tRefundUpdate.setRefundId(tRefund.getRefundId());
                tRefundUpdate.setRefundStatus(RefundStatusEnum.SUCCESSFULREFUND);
                tRefundMapper.updateByPrimaryKeySelective(tRefund);
            }
        }
        return tRefund;
    }
}
