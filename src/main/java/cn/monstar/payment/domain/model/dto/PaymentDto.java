package cn.monstar.payment.domain.model.dto;

import cn.monstar.payment.domain.model.mybatis.gen.TPayment;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangxianding
 * @version 1.0
 * @description 支付Dto
 * @date 2017/11/23 下午1:54
 */
public class PaymentDto extends TPayment implements Serializable {

	private static final long serialVersionUID = -7159533952242353972L;

	private Date timeEnd;

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}
}
