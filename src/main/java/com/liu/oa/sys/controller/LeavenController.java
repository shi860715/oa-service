package com.liu.oa.sys.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liu.oa.common.RequestHolder;
import com.liu.oa.framwork.utils.JacksonUtil;
import com.liu.oa.sys.model.Leave;
import com.liu.oa.sys.service.LeaveService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/sys/leave")
@Slf4j
public class LeavenController {
	
	@Autowired
	private LeaveService leaveService;
	
	
	@RequestMapping("/toleaves")
	 public String toLeaves(Model model) {
		
		 
		model.addAttribute("user", RequestHolder.getCurrentUser());
		 return "/leave/leaveList";
	 }
	
	

	@RequestMapping("/leavesByUserId")
	public 	@ResponseBody Map<String, Object> leavesByUserId(String query,int page,int rows){
		Map<String, Object> result  = new HashMap<>();
	
		
		log.info("参数：query {} page {} rows {}",query,page,rows);
		
		
	try {
			result=leaveService.findAllByUserId( query, page, rows);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   log.info(" 打印请假单{}",JacksonUtil.printJson(result));
	   return	result;
	}
	
	@RequestMapping("/saveOrUpdate")
	public 	@ResponseBody Map<String, Object> saveOrUpdate(@RequestBody Leave leave){
		Map<String, Object> result  = new HashMap<>();
	
		if(leave.getLeaveId()!=null) {
			
			try {
				leaveService.update(leave);
			} catch (Exception e) {
				
			}
			
		}else {
			try {
				leaveService.save(leave);
			} catch (Exception e) {
				
				
			}
			
			
		}
	
		
		
	   return	result;
	}
	

}
