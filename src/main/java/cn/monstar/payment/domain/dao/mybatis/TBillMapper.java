package cn.monstar.payment.domain.dao.mybatis;

import cn.monstar.payment.domain.dao.BaseMapper;
import cn.monstar.payment.domain.model.mybatis.gen.TBill;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhangshuai
 * @version 1.0
 * @description
 * @date 2017/11/27 15:30
 */
@Mapper
public interface TBillMapper extends BaseMapper<TBill, Long> {
}
