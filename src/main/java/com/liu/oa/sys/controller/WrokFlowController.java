package com.liu.oa.sys.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.liu.oa.common.RequestHolder;
import com.liu.oa.framwork.utils.IOUtil;
import com.liu.oa.framwork.utils.JacksonUtil;
import com.liu.oa.sys.model.User;
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
	
	
	@RequestMapping("/totasks")
	public String totasks() {
		
		return "/wrokFlow/tasks";
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
	public Map<String,Object> delete(String deploymentId,boolean flag){
		Map<String,Object> result = new HashMap<>();
		
			try {
				result =wrokFlowService.deleteDefinitionByDeployMentId(deploymentId,flag);
				log.info("【删除流程】{}",result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
		
		
		return result;
		
	}
	
	/**
	 * 通过部署的流程来获取流程定义图
	 * @param deploymentId
	 * @param response
	 */
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
	
	/**
	 * 通过部署的流程来获取流程定义文件
	 * @param deploymentId
	 * @param response
	 */
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
	
	
	
	@RequestMapping("/tasks")
	@ResponseBody
	public Map<String,Object> taskByUserId(int page,int rows,String query){
		
		Map<String,Object> result = new HashMap<>();
		User user =RequestHolder.getCurrentUser();
		try {
			result=wrokFlowService.getTaskList(user.getUserId().toString(), page, rows,query);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	
	@RequestMapping("/detailTask")
	public String taskInfo(String taskId,@ModelAttribute("fromId") String fromId ,RedirectAttributes model) {
	String key ="";	
	  try {
		Task task=	wrokFlowService.getTaskById(taskId);
		
	   
		ExecutionEntity ex =wrokFlowService.getExecutionByID(task.getExecutionId());
		String businessKey = ex.getBusinessKey();
		String params[] =businessKey.split(":");
		log.info("【任务跳转中心】业务值",businessKey);
	     key=params[0];
	    
	    
	     model.addAttribute("fromId",params[1]);
	    
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return "redirect:/sys/"+key+"/detail";
	}
	
	
	@RequestMapping("/showImage")
	public String showImage(String processInstanceId ,Model model) {
		
		String deploymentId =wrokFlowService.getDeployMentIdByProcessIntanceId(processInstanceId);
		
		String path ="/sys/workFlow/showDefinitionImage?deploymentId="+deploymentId;
		model.addAttribute("path", path);
		
		ActivityImpl act =wrokFlowService.getActivityImpl(processInstanceId);
		Map<String,Object> actInfo =new HashMap<>();
		
		actInfo.put("height", act.getHeight());
		actInfo.put("width", act.getWidth());
		actInfo.put("x", act.getY());
		actInfo.put("y", act.getX());
		
		model.addAttribute("actInfo", actInfo);
	
		
		
		
		return "/wrokFlow/showImage";
	}
	
	
}
