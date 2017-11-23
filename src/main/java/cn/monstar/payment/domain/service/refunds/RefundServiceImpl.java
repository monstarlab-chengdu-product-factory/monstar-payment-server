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
		// 判断支付类型，确定退款接口
		switch (tPayment.getPaymentType()) {
			case WECHAT:
				break;
			case ALIPAY:
				break;
			case APPLEPAY:
				break;
			case UNIONPAY:
				break;
		}


		return null;
	}
}
