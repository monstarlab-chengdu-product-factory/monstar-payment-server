package cn.monstar.payment.domain.service.refunds;

import cn.monstar.payment.domain.model.dto.RefundDto;
import cn.monstar.payment.domain.model.mybatis.gen.TRefund;
import cn.monstar.payment.domain.service.BaseService;
import cn.monstar.payment.web.controller.form.RefundForm;

/**
 * @author wangxianding
 * @version 1.0
 * @description 退款服务
 * @date 2017/11/22 下午6:10
 */
public interface RefundService extends BaseService<TRefund, Long> {

	/**
	 * 申请退款
	 * @param refundForm
	 * @return
	 */
	RefundDto refundApplication(RefundForm refundForm);

}
