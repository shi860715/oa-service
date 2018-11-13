package com.liu.oa.common;

import lombok.Getter;

@Getter
public enum ReslutEmnu  implements CodeEmnu {
	
	LOGIN_FAIL(1001,"用户名或者密码错误"),
	DEPT_TREE_FAIL(2001,"部门树获取失败"),
	USER_NOT_EXSIT(3001,"用户不存在"),
	USER_LOGIN_FAILED(4001,"用户名或者密码不正确"),
	USER_PARAM_ERROR(4002,"用户名或者密码不能为空"),
	
	
	;

	private Integer code;
	
	
	private String message;


	private ReslutEmnu(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	
	

}
