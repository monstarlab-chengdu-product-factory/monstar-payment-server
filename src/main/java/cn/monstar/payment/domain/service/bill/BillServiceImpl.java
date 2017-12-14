package cn.monstar.payment.domain.service.bill;

import cn.monstar.payment.domain.dao.mybatis.TBillMapper;
import cn.monstar.payment.domain.model.dto.PaymentDto;
import cn.monstar.payment.domain.model.mybatis.gen.TBill;
import cn.monstar.payment.domain.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangshuai
 * @version 1.0
 * @description
 * @date 2017/11/27 15:29
 */
@Service
public class BillServiceImpl extends BaseServiceImpl<TBill, Long, TBillMapper> implements BillService {

    @Override
    @Autowired
    public void setRepository(TBillMapper repository) {
        super.repository = repository;
    }

    @Override
    public int updatePaymentBill(PaymentDto paymentDto) {
        TBill bill = super.repository.findByPaymentId(paymentDto.getPaymentId());
        bill.setBillFinishDt(paymentDto.getTimeEnd());
        return super.repository.updateByPrimaryKey(bill);
    }
}
