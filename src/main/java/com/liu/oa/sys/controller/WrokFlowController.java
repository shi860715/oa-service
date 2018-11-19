package com.liu.oa.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liu.oa.sys.model.MyProcessDefinition;
import com.liu.oa.sys.service.WorkFlowService;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/sys/workFlow")
@Slf4j
public class WrokFlowController {
	
	@Autowired
	WorkFlowService wrokFlowService;
	
	
	
	@RequestMapping("/toProcessDefinition")
	public String toProcessDefinition() {
		
		return "/wrokFlow/processDefinition";
	}
	
	@RequestMapping("/processDefinitions")
	@ResponseBody
	public Map<String,Object> processDefinitions(int page,int rows,String query){
		Map<String,Object> result = new HashMap<>();
	
		
		try {
			result=wrokFlowService.getProcessDefinitions(page, rows, query);
			log.info("流程信息列表{}",result);
			
			
		} catch (Exception e) {
			result.put("total", 0);
			result.put("rows", new ArrayList<>());
			
			
		}
		
		
		return result;
	}
	

}
