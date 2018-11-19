package com.liu.oa.sys.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
	
	
	@RequestMapping("/addDeployment")
	 public Map<String,Object> addDeployment(HttpServletRequest request ,MultipartFile file) {
		Map<String,Object> result = new HashMap<>();
		String fileName=request.getParameter("fileName");
		 CommonsMultipartFile cFile =(CommonsMultipartFile) file;
		  try {
			  InputStream inputStream=cFile.getInputStream();
			  wrokFlowService.addDeploy(fileName,inputStream);
			  result.put("code", 1);
			  result.put("message", "流程部署成功");
			  
			  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return result;
		 
		 
	 }
	

}
