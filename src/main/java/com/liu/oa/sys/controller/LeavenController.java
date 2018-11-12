package com.liu.oa.sys.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liu.oa.sys.service.LeaveService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/sys/leave")
@Slf4j
public class LeavenController {
	
	private LeaveService leaveService;
	
	
	@RequestMapping("/toleaves")
	 public String toLeaves() {
		
		 return "/leave/leaveList";
	 }
	
	

	@RequestMapping("/leavesByUserId")
	public 	@ResponseBody Map<String, Object> leavesByUserId(String query,int page,int rows){
		Map<String, Object> result  = new HashMap<>();
		
		try {
			result=leaveService.findAllByUserId(9, query, page, rows);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   return	result;
	}
	
	
	

}
