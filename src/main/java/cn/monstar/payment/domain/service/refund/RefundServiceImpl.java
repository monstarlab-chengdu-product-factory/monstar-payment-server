package cn.monstar.payment.domain.service.refund;

import cn.monstar.payment.domain.dao.mybatis.TRefundMapper;
import cn.monstar.payment.domain.model.mybatis.gen.TRefund;
import cn.monstar.payment.domain.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangshuai
 * @version 1.0
 * @description
 * @date 2017/11/27 15:36
 */
public class RefundServiceImpl extends BaseServiceImpl<TRefund, Long, TRefundMapper> implements RefundService {

    @Override
    @Autowired
    public void setRepository(TRefundMapper repository) {
        super.repository = repository;
    }
}
