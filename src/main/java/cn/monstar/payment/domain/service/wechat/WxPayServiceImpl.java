package cn.monstar.payment.domain.service.wechat;

import cn.monstar.payment.domain.model.dto.PaymentDto;
import cn.monstar.payment.domain.model.enums.AccessTypeEnum;
import cn.monstar.payment.domain.model.enums.PaymentStatusEnum;
import cn.monstar.payment.domain.model.enums.PaymentTypeEnum;
import cn.monstar.payment.domain.model.mybatis.gen.TPayment;
import cn.monstar.payment.domain.service.bill.BillService;
import cn.monstar.payment.domain.service.payment.PaymentService;
import cn.monstar.payment.domain.util.QrCodeUtil;
import cn.monstar.payment.domain.util.constant.WxConstantUtil;
import cn.monstar.payment.domain.util.wechat.request.WxPayUnifiedOrderRequest;
import cn.monstar.payment.domain.util.wechat.response.WxPayCloseOrderResponse;
import cn.monstar.payment.domain.util.wechat.response.WxPayOrderQueryResponse;
import cn.monstar.payment.domain.util.wechat.response.WxPayUnifiedOrderResponese;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangxianding
 * @version 1.0
 * @description 微信支付服务
 * @date 2017/12/5 上午10:32
 */
@Service
public class WxPayServiceImpl extends AbstractWxPayService {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private BillService billService;

    @Transactional
    @Override
    public TPayment tradeQuery(String paymentNo) {
        logger.info("create trade query of wechatpay request. paymentNo is :{}", paymentNo);
        TPayment payment = paymentService.findByPaymentNo(paymentNo);
        TPayment tmpPayment = null;

        if (payment == null) {
            return null;
        }
        // 只处理微信支付的交易
        if (payment.getPaymentType() != PaymentTypeEnum.WECHAT) {
            return null;
        }

        // 检查支付状态。如果是已支付、支付完成、支付失败则直接返回
        if (payment.getPaymentStatus() == PaymentStatusEnum.CLOSED || payment.getPaymentStatus() == PaymentStatusEnum.PAID
                || payment.getPaymentStatus() == PaymentStatusEnum.PAYMENTFAILURE) {
            return payment;
        }
        // 同步获取微信服务器交易结果
        try {
            tmpPayment = new TPayment();
            WxPayOrderQueryResponse wxPayOrderQueryResponse = this.wxOrderQuery(null, paymentNo);
            switch (wxPayOrderQueryResponse.getTradeType()) {
                case WxConstantUtil.TRADE_STATE_CLOSED:
                    tmpPayment.setPaymentStatus(PaymentStatusEnum.CLOSED);
                    break;
                case WxConstantUtil.TRADE_STATE_PAYERROR:
                    tmpPayment.setPaymentStatus(PaymentStatusEnum.PAYMENTFAILURE);
                    break;
                case WxConstantUtil.TRADE_STATE_SUCCESS:
                    tmpPayment.setPaymentStatus(PaymentStatusEnum.PAID);
                    tmpPayment.setOutTradeNo(wxPayOrderQueryResponse.getTransactionId());
                    break;
                default:
                    break;
            }
            tmpPayment.setPaymentId(payment.getPaymentId());
            paymentService.update(tmpPayment);

            // 记入流水
            if (tmpPayment.getPaymentStatus() == PaymentStatusEnum.PAID) {
                DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("yyyyMMddHHmmss");
                DateTime dateTime = DateTime.parse(wxPayOrderQueryResponse.getTimeEnd(), dateTimeFormat);

                PaymentDto paymentDto = new PaymentDto();
                BeanUtils.copyProperties(tmpPayment, paymentDto);
                paymentDto.setTimeEnd(dateTime.toDate());
                billService.updatePaymentBill(paymentDto);
            }
            return payment;
        } catch (Exception e) {
            logger.error("Query the failure of WeChat payment transaction:{}", e.getMessage());
            return null;
        }
    }

    @Override
    public String createPay(String paymentNo, AccessTypeEnum accessType, String clientIp) {
        TPayment payment = paymentService.findByPaymentNo(paymentNo);
        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest.Builder()
                .setOutTradeNo(paymentNo)
                .setBody(payment.getDescription())
                .setSpbillCreateIp(clientIp)
                .setTradeType(accessType == AccessTypeEnum.QRCODE ? WxConstantUtil.TRADE_NATIVE : WxConstantUtil.TRADE_H5)
                .setProductId(accessType == AccessTypeEnum.QRCODE ? paymentNo : "")
                .newBuiler();
        WxPayUnifiedOrderResponese responese = this.wxUnifiedOrder(request);
        if (accessType == AccessTypeEnum.H5) {
            return responese.getMwebUrl();
        } else {
            return QrCodeUtil.generateQrCode(responese.getCodeUrl());
        }
    }

    @Override
    public void closeOrder(String paymentNo) {
        WxPayCloseOrderResponse response = this.wxCloseOrder(paymentNo);
        if ("FAIL".equals(response.getReturnCode())) {
            //throw new BusinessException();
        }
    }
}
