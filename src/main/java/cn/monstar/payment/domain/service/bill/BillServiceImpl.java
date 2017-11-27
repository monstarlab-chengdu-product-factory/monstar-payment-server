package cn.monstar.payment.domain.service.bill;

import cn.monstar.payment.domain.dao.mybatis.TBillMapper;
import cn.monstar.payment.domain.model.mybatis.gen.TBill;
import cn.monstar.payment.domain.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangshuai
 * @version 1.0
 * @description
 * @date 2017/11/27 15:29
 */
public class BillServiceImpl extends BaseServiceImpl<TBill, Long, TBillMapper> implements BillService {

    @Override
    @Autowired
    public void setRepository(TBillMapper repository) {
        super.repository = repository;
    }
}
