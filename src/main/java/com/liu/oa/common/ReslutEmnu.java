package com.liu.oa.common;

import lombok.Getter;

@Getter
public enum ReslutEmnu  implements CodeEmnu {
	
	LOGIN_FAIL(1001,"用户名或者密码错误"),
	
	
	
	;

	private Integer code;
	
	
	private String message;


	private ReslutEmnu(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	
	

}
