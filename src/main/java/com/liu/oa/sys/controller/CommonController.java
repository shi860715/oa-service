package com.liu.oa.sys.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liu.oa.common.RequestHolder;
import com.liu.oa.common.contants.CookiesConstant;
import com.liu.oa.framwork.utils.CookiesUtil;
import com.liu.oa.sys.model.User;
import com.liu.oa.sys.service.RedisService;

@Controller
public class CommonController {
	
	@Autowired
	RedisService redisService;
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		
		return "login";
	
	}
	
	@RequestMapping("/index")
	public String index(Model model) {
		
	User user =	RequestHolder.getCurrentUser();
	
	model.addAttribute("user",user);
		return "index";
		
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request ,HttpServletResponse response) {
		
	
	Cookie c =CookiesUtil.getCookie(request, CookiesConstant.COOKIES_NAME);
	CookiesUtil.setCookie(CookiesConstant.COOKIES_NAME, "", 0, response);
	redisService.remove(c.getValue());
	
	
	
		return "redirect:/tologin";
		
	}
	
	
	
	@RequestMapping("/roleComm")
	public String toRoleCom() {
		
		return "/commons/roleCom";
		
	}
	
	@RequestMapping("/menuTreeComm")
	public String menuTreeComm() {
		
		return "/commons/menuTreeCom";
		
	}
	
	@RequestMapping("/tologin")
	public String login() {
		
	
		return "login";
		
	}
	
	@RequestMapping("/tologinerror")
	public String loginerror() {
		
		return "/loginerror";
		
	}
	
	@RequestMapping("/common/index")
	public String commonindex() {
		
		return "/commons/index";
		
	}
}
