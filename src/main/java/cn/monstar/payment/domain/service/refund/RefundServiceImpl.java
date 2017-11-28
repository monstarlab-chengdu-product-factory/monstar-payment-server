package cn.monstar.payment.domain.service.refund;

import cn.monstar.payment.config.MonstarConfig;
import cn.monstar.payment.domain.dao.mybatis.TRefundMapper;
import cn.monstar.payment.domain.model.dto.ApplyRefundResultDto;
import cn.monstar.payment.domain.model.enums.PaymentStatusEnum;
import cn.monstar.payment.domain.model.enums.RefundStatusEnum;
import cn.monstar.payment.domain.model.mybatis.gen.TPayment;
import cn.monstar.payment.domain.model.mybatis.gen.TRefund;
import cn.monstar.payment.domain.service.BaseServiceImpl;
import cn.monstar.payment.domain.service.payment.PaymentService;
import cn.monstar.payment.domain.util.StringUtil;
import cn.monstar.payment.web.controller.form.ApplyRefundForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangshuai
 * @version 1.0
 * @description
 * @date 2017/11/27 15:36
 */
@Service
@Transactional
public class RefundServiceImpl extends BaseServiceImpl<TRefund, Long, TRefundMapper> implements RefundService {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private MonstarConfig monstarConfig;

    @Override
    @Autowired
    public void setRepository(TRefundMapper repository) {
        super.repository = repository;
    }

    @Override
    public ApplyRefundResultDto refundApplication(ApplyRefundForm applyRefundForm) {
        ApplyRefundResultDto applyRefundResultDto = null;
        Date currentDt = new Date();
        // 获取payment
        TPayment tPayment = paymentService.findByPaymentNo(applyRefundForm.getPaymentNo());
        // 构建refund
        if (tPayment == null) {
            throw new RuntimeException();
        }

        if (tPayment.getPaymentStatus() == PaymentStatusEnum.UNPAID || tPayment.getPaymentStatus() == PaymentStatusEnum.PAYMENTFAILURE) {
            // 付款单状态不正确
            throw new RuntimeException();
        }

        applyRefundResultDto = new ApplyRefundResultDto(new BigDecimal(applyRefundForm.getOrderMoney()), new BigDecimal(applyRefundForm.getRefundMoney()),
                applyRefundForm.getRefundDescription());
        applyRefundResultDto.setPaymentId(tPayment.getPaymentId());
        applyRefundResultDto.setRefundNo(StringUtil.uuidGenerator());
        applyRefundResultDto.setCreDt(currentDt);
        //存入退款表
        TRefund refund = new TRefund();
        BeanUtils.copyProperties(applyRefundResultDto, refund);
        super.repository.insert(refund);


        String outRefundNo = null;
        if (monstarConfig.getSandboxnew()) {
            //TODO 模拟生成退款单号
            outRefundNo = StringUtil.uuidGenerator();
            refund.setOutRefundNo(outRefundNo);
            refund.setRefundStatus(RefundStatusEnum.REFUNDPROCESSING);
        } else {
            // TODO 线上环境
            // 判断支付类型，调用支付平台申请退款接口
            switch (tPayment.getPaymentType()) {
                case WECHAT:
                    System.out.println("微信支付");
                    break;
                case ALIPAY:
                    System.out.println("支付宝支付");
                    break;
                case APPLEPAY:
                    System.out.println("苹果支付");
                    break;
                case UNIONPAY:
                    System.out.println("银联支付");
                    break;
            }
        }
        super.repository.updateByPrimaryKey(refund);
        BeanUtils.copyProperties(refund, applyRefundResultDto);
        return applyRefundResultDto;
    }

}
