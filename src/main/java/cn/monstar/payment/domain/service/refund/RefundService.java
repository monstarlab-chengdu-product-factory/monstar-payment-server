package cn.monstar.payment.domain.service.refund;

import cn.monstar.payment.domain.model.dto.ApplyRefundResultDto;
import cn.monstar.payment.domain.model.dto.RefundDto;
import cn.monstar.payment.domain.model.mybatis.gen.TRefund;
import cn.monstar.payment.domain.service.BaseService;
import cn.monstar.payment.web.controller.form.ApplyRefundForm;

/**
 * @author zhangshuai
 * @version 1.0
 * @description
 * @date 2017/11/27 15:24
 */
public interface RefundService extends BaseService<TRefund, Long> {

    /**
     * 申请退款
     *
     * @param applyRefundForm
     * @return
     */
    ApplyRefundResultDto refundApplication(ApplyRefundForm applyRefundForm);

}
