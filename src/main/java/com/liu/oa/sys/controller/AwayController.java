package com.liu.oa.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liu.oa.common.RequestHolder;
import com.liu.oa.common.enums.WorkFlowEmnu;
import com.liu.oa.sys.form.ComTaskForm;
import com.liu.oa.sys.model.Away;
import com.liu.oa.sys.model.Leave;
import com.liu.oa.sys.model.User;
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
		
		
		try {
			result=awayService.findAllByUserId(page,rows,query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}

	
	
	@RequestMapping("/completeTask")
	public @ResponseBody Map<String ,Object > completeTask(@RequestBody ComTaskForm taskForm){
		
		Map<String,Object> result = new HashMap<String, Object>();
	try {
		awayService.completeTask("away:"+taskForm.getId(),taskForm.getVariables());
		result.put("message", "申请任务提交成功");
		
		
	} catch (Exception e) {
	
		e.printStackTrace();
		result.put("message", e.getMessage());
	}
	return result;
	}
	
	
	
	
	@RequestMapping("/delete")
	public @ResponseBody Map<String,Object> delete(int awayId){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			awayService.deleteById(awayId);
			result.put("message", "出差申请删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("message", e.getMessage());
		}
		
		return result;
	}
	
	
	
	@RequestMapping("/detail")
	public String detail(String fromId,Model model) {
		
		
		try {
			Away away =awayService.selectById(Integer.parseInt(fromId));
			User user = userService.selectById(away.getUserId());
			String businessKey ="away:"+away.getAwayId();
			
			List<PvmTransition> outcoms=workFlowService.getPvmTransitionByBusinessKey(businessKey);
			Task task =workFlowService.getTaskByBusinessKey(businessKey);
			String taskId=task.getId();
			
			
			List<String> buttons=outcoms.stream().map(x -> {
			
				return (String)x.getProperty("name");
			}).collect(Collectors.toList());
			
			log.info("leaveINfo{}",away);
			
			model.addAttribute("taskId",taskId);
			model.addAttribute("away",away);
			model.addAttribute("user",user);
			model.addAttribute("buttons",buttons);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return "/away/awayDetail";
		
	}
	
	
	
	
	
}
