package cn.monstar.payment.domain.service.payment;

import cn.monstar.payment.config.MessageConfig;
import cn.monstar.payment.domain.dao.mybatis.TPaymentMapper;
import cn.monstar.payment.domain.model.dto.PayDto;
import cn.monstar.payment.domain.model.dto.PayQueryDto;
import cn.monstar.payment.domain.model.enums.AccessTypeEnum;
import cn.monstar.payment.domain.model.enums.PaymentStatusEnum;
import cn.monstar.payment.domain.model.enums.PaymentTypeEnum;
import cn.monstar.payment.domain.model.mybatis.gen.TPayment;
import cn.monstar.payment.domain.service.BaseServiceImpl;
import cn.monstar.payment.domain.service.alipay.AlipayService;
import cn.monstar.payment.domain.util.StringUtil;
import cn.monstar.payment.web.controller.form.PayForm;
import cn.monstar.payment.web.error.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangshuai
 * @version 1.0
 * @description
 * @date 2017/11/27 15:35
 */
@Service
public class PaymentServiceImpl extends BaseServiceImpl<TPayment, Long, TPaymentMapper> implements PaymentService {

    @Autowired
    private AlipayService alipayService;

    @Autowired
    @Override
    public void setRepository(TPaymentMapper repository) {
        super.repository = repository;
    }

    @Override
    public boolean paymentCorrectCheck(String paymentNo, BigDecimal orderMoney) {
        if (super.repository.paymentCorrectCheck(paymentNo, orderMoney) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public PayDto createPayment(PayForm payForm) {
        Date now = new Date();
        TPayment tPayment = new TPayment();
        tPayment.setPaymentNo(getPaymentNo());
        tPayment.setPaymentType(PaymentTypeEnum.valueOf(payForm.getPaymentType()));
        tPayment.setPaymentStatus(PaymentStatusEnum.UNPAID);
        tPayment.setOrderMoney(payForm.getOrderMoney());
        tPayment.setGoodsInfo(payForm.getGoodsInfo());
        if (payForm.getGoodsDetails() != null && !"".equals(payForm.getGoodsDetails())) {
            tPayment.setGoodsDetails(payForm.getGoodsDetails());
        }
        if (payForm.getUserNo() != null && !"".equals(payForm.getUserNo())) {
            tPayment.setUserNo(payForm.getUserNo());
        }
        if (payForm.getDescription() != null && !"".equals(payForm.getDescription())) {
            tPayment.setDescription(payForm.getDescription());
        }
        if (payForm.getOrderCreDt() != null && !"".equals(payForm.getOrderCreDt())) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            try {
                tPayment.setOrderCreDt(simpleDateFormat.parse(payForm.getOrderCreDt()));
            }catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException(MessageConfig.E00015);
            }
        } else {
            tPayment.setOrderCreDt(now);
        }
        if (payForm.getExpireTimes() != null) {
            tPayment.setExpireTimes(payForm.getExpireTimes());
        }
        tPayment.setCreDt(now);
        super.repository.insertSelective(tPayment);
        PayDto payDto = new PayDto();
        payDto.setPaymentNo(tPayment.getPaymentNo());
        return payDto;
    }

    @Override
    public String payRequest(String paymentNo, AccessTypeEnum accessType) {
        TPayment tPayment = super.repository.findByPaymentNo(paymentNo);
        if(tPayment == null) {
            throw new BusinessException(String.format(MessageConfig.E00002, paymentNo));
        }
        if(tPayment.getPaymentStatus() != PaymentStatusEnum.UNPAID) {
            throw new BusinessException(String.format(MessageConfig.E00003, paymentNo));
        }
        switch (tPayment.getPaymentType()) {
            case WECHAT:
                break;
            case ALIPAY:
                try {
                    if (accessType == AccessTypeEnum.H5) {
                        return alipayService.tradeWapPay(paymentNo);
                    } else {
                        return alipayService.tradePagePay(paymentNo);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case APPLEPAY:
                break;
            case UNIONPAY:
                break;
        }
        return "";
    }

    @Override
    public PayQueryDto paymentQuery(String paymentNo) {
        TPayment tPayment = super.repository.findByPaymentNo(paymentNo);
        if(tPayment == null) {
            throw new BusinessException(String.format(MessageConfig.E00002, paymentNo));
        }
        if (tPayment.getPaymentStatus() != PaymentStatusEnum.PAID) {
            switch (tPayment.getPaymentType()) {
                case WECHAT:
                    break;
                case ALIPAY:
                    tPayment = alipayService.tradeQuery(paymentNo);
                    break;
                case APPLEPAY:
                    break;
                case UNIONPAY:
                    break;
            }
        }
        PayQueryDto payQueryDto = new PayQueryDto();
        BeanUtils.copyProperties(tPayment, payQueryDto);
        payQueryDto.setPaymentType(tPayment.getPaymentType().getEnumValue());
        payQueryDto.setPaymentStatus(tPayment.getPaymentStatus().getEnumValue());
        return payQueryDto;
    }

    private String getPaymentNo() {
        String randomNum = StringUtil.getRandomString(3, StringUtil.RANDOM_ONLY_NUMBER);
        return (new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + randomNum);
    }

    @Override
    public void updateToFinish(String paymentNo, String outTradeNo) {
        super.repository.updateToFinish(paymentNo, outTradeNo);
    }

    @Override
    public TPayment findByPaymentNo(String paymentNo) {
        return super.repository.findByPaymentNo(paymentNo);
    }
}
