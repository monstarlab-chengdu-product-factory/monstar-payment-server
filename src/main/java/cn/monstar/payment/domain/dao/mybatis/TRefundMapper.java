package cn.monstar.payment.domain.dao.mybatis;

import cn.monstar.payment.domain.dao.BaseMapper;
import cn.monstar.payment.domain.model.mybatis.gen.TRefund;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangxianding
 * @version 1.0
 * @description 退款Mapper
 * @date 2017/11/22 下午6:18
 */
@Mapper
public interface TRefundMapper extends BaseMapper<TRefund, Long>{
}
