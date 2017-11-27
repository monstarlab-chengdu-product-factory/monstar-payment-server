package cn.monstar.payment.domain.service.refunds;

import cn.monstar.payment.domain.dao.mybatis.TRefundMapper;
import cn.monstar.payment.domain.model.dto.PaymentDto;
import cn.monstar.payment.domain.model.dto.RefundDto;
import cn.monstar.payment.domain.model.enums.ExceptionEnum;
import cn.monstar.payment.domain.model.enums.PaymentStatusEnum;
import cn.monstar.payment.domain.model.mybatis.gen.TPayment;
import cn.monstar.payment.domain.model.mybatis.gen.TRefund;
import cn.monstar.payment.domain.service.BaseServiceImpl;
import cn.monstar.payment.domain.service.payment.PaymentService;
import cn.monstar.payment.domain.util.StringUtil;
import cn.monstar.payment.web.controller.form.RefundForm;
import cn.monstar.payment.web.exception.CheckedException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangxianding
 * @version 1.0
 * @description 退款Service
 * @date 2017/11/22 下午6:11
 */
@Service
@Transactional
public class RefundServiceImpl extends BaseServiceImpl<TRefund, Long, TRefundMapper> implements RefundService {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	@Override
	public void setRepository(TRefundMapper repository) {
		super.repository = repository;
	}

	@Override
	public RefundDto refundApplication(RefundForm refundForm) {
		RefundDto refundDto = null;
		// 获取payment
		TPayment tPayment = paymentService.findByPaymentNo(refundForm.getPaymentNo());
		// 构建refund
		if(tPayment == null){
			throw new CheckedException(ExceptionEnum.NOF_FOUND_PAYMENT.getEnumValue(), ExceptionEnum.NOF_FOUND_PAYMENT.getLabel());
		}
		// 付款单状态检查
		if (tPayment.getPaymentStatus() == PaymentStatusEnum.UNPAID || tPayment.getPaymentStatus() == PaymentStatusEnum.PAYMENTFAILURE) {

		}

		refundDto = new RefundDto();
		BeanUtils.copyProperties(refundForm, refundDto);
		refundDto.setPaymentId(tPayment.getPaymentId());
		refundDto.setRefundNo(StringUtil.uuidGenerator());
		//存入退款表
		TRefund refund = new TRefund();
		BeanUtils.copyProperties(refundDto, refund);
		super.repository.insert(refund);

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

		return refundDto;
	}
}
