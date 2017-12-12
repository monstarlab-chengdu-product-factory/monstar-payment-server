package cn.monstar.payment.domain.dao.mybatis;

import cn.monstar.payment.domain.dao.BaseMapper;
import cn.monstar.payment.domain.model.enums.PaymentStatusEnum;
import cn.monstar.payment.domain.model.mybatis.gen.TPayment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author zhangshuai
 * @version 1.0
 * @description
 * @date 2017/11/27 15:32
 */
@Mapper
public interface TPaymentMapper extends BaseMapper<TPayment, Long> {

    /**
     * find payment info by paymentNo
     *
     * @param paymentNo
     * @return
     */
    TPayment findByPaymentNo(@Param("paymentNo") String paymentNo);

    /**
     * check paymentNo exist and orderMoney is right
     *
     * @return
     */
    Integer paymentCorrectCheck(@Param("paymentNo") String paymentNo, @Param("orderMoney") BigDecimal orderMoney);

    /**
     * update payment to finish by paymentNo
     */
    void updateToFinish(@Param("paymentNo") String paymentNo, @Param("outTradeNo") String outTradeNo);
}
