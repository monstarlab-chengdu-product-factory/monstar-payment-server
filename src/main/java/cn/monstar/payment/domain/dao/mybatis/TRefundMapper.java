package cn.monstar.payment.domain.dao.mybatis;

import cn.monstar.payment.domain.dao.BaseMapper;
import cn.monstar.payment.domain.model.mybatis.gen.TRefund;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhangshuai
 * @version 1.0
 * @description
 * @date 2017/11/27 15:33
 */
@Mapper
public interface TRefundMapper extends BaseMapper<TRefund, Long> {

    /**
     * query refund info by refundNo
     * @param refundNo
     * @return
     */
    TRefund findByRefundNo(@Param("refundNo") String refundNo);
}
