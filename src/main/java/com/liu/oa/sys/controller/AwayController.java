package com.liu.oa.sys.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liu.oa.sys.model.Away;
import com.liu.oa.sys.service.AwayService;
import com.liu.oa.sys.service.UserService;
import com.liu.oa.sys.service.WorkFlowService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/sys/away")
@Slf4j
public class AwayController {

	@Autowired
	private AwayService awayService;
	
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private WorkFlowService workFlowService;
	


	@RequestMapping("/toaways")
	public String toAways() {
		
		
		return "/away/awayList";
		
	}
	
	
	@RequestMapping("/saveOrUpdate")
	public @ResponseBody Map<String,Object> saveOrUpdate(@RequestBody Away away){
		
		Map<String,Object> result = new HashMap<String, Object>();
		log.info("【创建请假单】{}",away);
		
		try {
			awayService.saveAndStartProcess(away);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return result;
	}
	
	

}
