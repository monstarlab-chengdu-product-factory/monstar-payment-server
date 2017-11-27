package cn.monstar.payment.domain.dao.mybatis;

import cn.monstar.payment.domain.dao.BaseMapper;
import cn.monstar.payment.domain.model.mybatis.gen.TPayment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author wangxianding
 * @version 1.0
 * @description 支付Mapper
 * @date 2017/11/23 下午1:44
 */
@Mapper
public interface TPaymentMapper extends BaseMapper<TPayment, Long> {

	/**
	 * 根据支付流水号获取支付信息
	 *
	 * @param paymentNo 支付流水号
	 * @return
	 */
	TPayment findByPaymentNo(@Param("paymentNo") String paymentNo);
}
