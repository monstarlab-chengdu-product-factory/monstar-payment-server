package cn.monstar.payment.domain.service.bill;

import cn.monstar.payment.domain.model.dto.PaymentDto;
import cn.monstar.payment.domain.model.mybatis.gen.TBill;
import cn.monstar.payment.domain.service.BaseService;

/**
 * @author zhangshuai
 * @version 1.0
 * @description
 * @date 2017/11/27 15:23
 */
public interface BillService extends BaseService<TBill, Long> {

    /**
     * 记入付款流水完结信息
     *
     * @param paymentDto 支付流水
     * @return
     */
    int updatePaymentBill(PaymentDto paymentDto);
}
