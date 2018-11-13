package com.liu.oa.framwork.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest srequest, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		  HttpServletRequest request = (HttpServletRequest) srequest;
		  
		  
		  
		  
		  
		  
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
