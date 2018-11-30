package com.liu.oa.sys.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liu.oa.common.RequestHolder;
import com.liu.oa.common.enums.WorkFlowEmnu;
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
		
		
		away.setStatus(WorkFlowEmnu.STATUS_UNPOST.getCode());
		if(away.getAwayId()!=null) {
			
			try {
				awayService.update(away);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			
			try {
				result=awayService.saveAndStartProcess(away);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		return result;
	}
	
	@RequestMapping("/awaysByUserId")
	public @ResponseBody Map<String ,Object > awaysByUserId(int page,int rows,String query){
		Map<String,Object> result = new HashMap<String, Object>();
		
		
		result=awayService.findAllByUserId(page,rows,query);
		
		
		return result;
	}

	
	
	@RequestMapping("/completeTask")
	public @ResponseBody Map<String ,Object > completeTask(String processId){
		
		Map<String,Object> result = new HashMap<String, Object>();
		result =awayService.completeTask(processId);
		return result;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
