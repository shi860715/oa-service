package com.liu.oa.common.enums;

import lombok.Getter;

@Getter
public enum SexEmnu implements CodeEmnu{
	
    MALE_CODE(1,"男"),
    FEMALE_CODE(0,"女"),

	;
	
	private Integer code;
	
	private String msg;

	
	 SexEmnu(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}


	

}
