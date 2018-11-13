package com.liu.oa.sys.advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.liu.oa.sys.exception.UserLoginException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	
	@ExceptionHandler(UserLoginException.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	
	public String handlerUserLoginException(UserLoginException exception,Model model) {
		
		
		model.addAttribute("msg", exception.getMessage());
		
		return "forward:/tologin";
	}
	
	
	

	
	
	
	

}
