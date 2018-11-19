package com.liu.oa.sys.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.liu.oa.sys.exception.UserLoginException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	
	@ExceptionHandler(UserLoginException.class)
	public String handlerUserLoginException(UserLoginException exception,RedirectAttributes arrt) {
		
		arrt.addFlashAttribute("msg", exception.getMessage());
		
		
		return "redirect:/tologin";
	}
	
	
	

	
	
	
	

}
