package com.liu.oa.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/sys/costpay")
@Slf4j
@Controller
public class CostPayController {
	
	
	
	@RequestMapping("/detail")
	public String detail(String fromId) {
		
		log.info("【报销处理】");
		
		return "/costpay/costpayDetail";
	}
	
	

}
