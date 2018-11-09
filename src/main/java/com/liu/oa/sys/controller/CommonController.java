package com.liu.oa.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		
		return "login";
	
	}
	
	@RequestMapping("/index")
	public String index() {
		
		return "index";
		
	}
	
	@RequestMapping("/roleComm")
	public String toRoleCom() {
		
		return "/commons/roleCom";
		
	}
	
	@RequestMapping("/menuTreeComm")
	public String menuTreeComm() {
		
		return "/commons/menuTreeCom";
		
	}

}
