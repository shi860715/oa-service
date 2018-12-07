package com.liu.oa.common.enums;

import lombok.Getter;

@Getter
public enum StatusEmnu implements CodeEmnu{
	
    ENABLE_STATUS(1,"启用"),
    SHUTDWON_STATUS(0,"停用"),

	;
	
	private Integer code;
	
	private String msg;

	
	 StatusEmnu(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}


	

}
