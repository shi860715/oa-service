package com.liu.oa.common;

import lombok.Getter;

@Getter
public enum LeaveEmnu implements CodeEmnu{
	
	LEAVE_S(1,"事假"),
	LEAVE_J(2,"加班"),
	LEAVE_T(3,"调休"),
	LEAVE_H(4,"婚假"),
	LEAVE_C(5,"产假"),
	LEAVE_D(6,"丧假"),
	LEAVE_B(7,"病假"),
	
	LEAVE_STATUS_UNPOST(11,"待提交"),
	LEAVE_STATUS_WATING(12,"审核中"),
	LEAVE_STATUS_FAILED(13,"审核不通过"),
	LEAVE_STATUS_SUCCESS(14,"审核通过"),
	;
	
	private Integer code;
	
	private String msg;

	
	 LeaveEmnu(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}


	

}
