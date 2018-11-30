package com.liu.oa.common.enums;

import lombok.Getter;

@Getter
public enum WorkFlowEmnu implements CodeEmnu{
	

	
	STATUS_UNPOST(11,"待提交"),
	STATUS_WATING(12,"审核中"),
	STATUS_FAILED(13,"审核不通过"),
	STATUS_SUCCESS(14,"审核通过"),
	;
	
	private Integer code;
	
	private String msg;

	
	 WorkFlowEmnu(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}


	

}
