package com.liu.oa.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/sys/leave")
@Slf4j
public class LeavenController {
	
	
	@RequestMapping("/toleaves")
	 public String toLeaves() {
		
		 return "/leave/leaveList";
	 }
	
	
	
	
	
	

}
