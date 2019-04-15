package com.liu.oa.common.enums;

import lombok.Getter;

@Getter
public enum OutEmnu implements CodeEmnu{
	
	UN_TYPE_START(1,"上班未打卡"),
	UN_TYPE_FINSHED(2,"下班未打卡"),
	;
	
	private Integer code;
	
	private String msg;

	
	 OutEmnu(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}


	

}
