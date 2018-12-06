package com.liu.oa.common.enums;

import lombok.Getter;

@Getter
public enum CostPayEmnu implements CodeEmnu{
	
	COSTPAY_TYPE_CASH(1,"现金"),
	COSTPAY_TYPE_TRANSFER(2,"转账"),
	COSTPAY_TYPE_ALIPAY(3,"支付宝"),
	COSTPAY_TYPE_WECHAT(4,"微信"),

	;
	
	private Integer code;
	
	private String msg;

	
	 CostPayEmnu(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}


	

}
