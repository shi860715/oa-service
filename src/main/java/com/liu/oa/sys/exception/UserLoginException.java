package com.liu.oa.sys.exception;

import com.liu.oa.common.enums.ReslutEmnu;



public class UserLoginException extends RuntimeException{
	
	Integer code;
	
	public UserLoginException(ReslutEmnu resultEmnu) {
		super(resultEmnu.getMessage());
		this.code =resultEmnu.getCode();
	}
	
	
	public UserLoginException(Integer code,String message) {
			
			super(message);
			this.code =code;
			
			
		}
	
	

}
