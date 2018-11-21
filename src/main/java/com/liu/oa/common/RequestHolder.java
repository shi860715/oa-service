package com.liu.oa.common;

import javax.servlet.http.HttpServletRequest;

import com.liu.oa.sys.model.User;

/**
 *  用于存放 现成中的用户和request 对象
 * @author Administrator
 *
 */
public class RequestHolder {
	
	

	private static final ThreadLocal<User> userHolder = new ThreadLocal<>();
	
	
	private static final ThreadLocal<HttpServletRequest> requestHolder= new ThreadLocal<>();
	
	
	
	
	public static void add(User user) {
		userHolder.set(user);
	}
	
	
	public static void add(HttpServletRequest request) {
        requestHolder.set(request);
    }
	
	public static User getCurrentUser(){
	        return   userHolder.get();
	   }
	
	public static  HttpServletRequest getCurrentRequest(){
	       return  requestHolder.get();
	   }
	
	public static void remove() {
        userHolder.remove();
        requestHolder.remove();
    }
	

}
