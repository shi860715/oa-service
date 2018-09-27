package com.liu.oa.sys.exception;

import com.liu.oa.common.ReslutEmnu;


public class DeptException extends RuntimeException {
	
	
	private Integer code;
	
	
	public DeptException(ReslutEmnu resultEmnu) {
		super(resultEmnu.getMessage());
		this.code =resultEmnu.getCode();
	}
	
	public DeptException(Integer code,String message) {
		
		super(message);
		this.code =code;
		
		
	}
	

}
