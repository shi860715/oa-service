package com.liu.oa.common.enums;

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
	

	;
	
	private Integer code;
	
	private String msg;

	
	 LeaveEmnu(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}


	

}
