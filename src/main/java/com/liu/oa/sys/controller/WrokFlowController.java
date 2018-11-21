package com.liu.oa.sys.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.liu.oa.framwork.utils.IOUtil;
import com.liu.oa.framwork.utils.JacksonUtil;
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
	
	
	/**
	 * 分页查找流程定义，模糊查找query
	 * @param page
	 * @param rows
	 * @param query
	 * @return
	 */
	@RequestMapping("/definitions")
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
	
	/**
	 * 部署流程
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping("/addDeployment")
	@ResponseBody
	 public Map<String,Object> addDeployment(HttpServletRequest request ,MultipartFile file) {
		Map<String,Object> result = new HashMap<>();
		String fileName=request.getParameter("fileName");
		  try {
			  InputStream inputStream = file.getInputStream();
			  Deployment d= wrokFlowService.addDeploy(fileName,inputStream);
			  log.info("xxxx{}",JacksonUtil.printJson(d));
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
	
	
	/**
	 * 删除流程定义
	 * @param deploymentId
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String deploymentId){
		Map<String,Object> result = new HashMap<>();
		
		try {
			result =wrokFlowService.deleteDefinitionByDeployMentId(deploymentId);
			log.info("【删除流程】{}",result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@RequestMapping("/showDefinitionImage")
	public void showDefinitionImage(String deploymentId,HttpServletResponse response) {
			try {
				InputStream in =wrokFlowService.getPicByDeploymentId(deploymentId);
				IOUtil.outPutStream(in, response, "image/png");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
	}
	
	
	@RequestMapping("/showDefinitionFile")
	public void showDefinitionFile(String deploymentId,HttpServletResponse response) {
		
		try {
			InputStream in =wrokFlowService.getFileByDeploymentId(deploymentId);
			IOUtil.outPutStream(in, response, "text/xml");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}
