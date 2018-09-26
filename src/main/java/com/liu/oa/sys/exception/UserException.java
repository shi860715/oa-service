package com.liu.oa.sys.exception;

import com.liu.oa.common.ReslutEmnu;


public class UserException extends RuntimeException {
	
	
	private Integer code;
	
	
	public UserException(ReslutEmnu resultEmnu) {
		super(resultEmnu.getMessage());
		this.code =resultEmnu.getCode();
	}
	
	public UserException(Integer code,String message) {
		
		super(message);
		this.code =code;
		
		
	}
	

}
