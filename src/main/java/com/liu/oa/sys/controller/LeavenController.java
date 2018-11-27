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
import com.liu.oa.common.enums.LeaveEmnu;
import com.liu.oa.framwork.utils.JacksonUtil;
import com.liu.oa.sys.model.Leave;
import com.liu.oa.sys.model.User;
import com.liu.oa.sys.service.LeaveService;
import com.liu.oa.sys.service.UserService;
import com.liu.oa.sys.service.WorkFlowService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/sys/leave")
@Slf4j
public class LeavenController {

	@Autowired
	private LeaveService leaveService;
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private WorkFlowService workFlowService;
	


	@RequestMapping("/toleaves")
	public String toLeaves(Model model) {

		model.addAttribute("user", RequestHolder.getCurrentUser());
		return "/leave/leaveList";
	}

	@RequestMapping("/leavesByUserId")
	public @ResponseBody Map<String, Object> leavesByUserId(String query, int page, int rows) {
		Map<String, Object> result = new HashMap<>();

		log.info("参数：query {} page {} rows {}", query, page, rows);

		try {
			result = leaveService.findAllByUserId(query, page, rows);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		log.info(" 打印请假单{}", JacksonUtil.printJson(result));
		return result;
	}

	@RequestMapping("/saveOrUpdate")
	public @ResponseBody Map<String, Object> saveOrUpdate(@RequestBody Leave leave) {
		Map<String, Object> result = new HashMap<>();
		log.info("【请假单保存或者更新】参数{}",JacksonUtil.toJSon(leave));
		
	
	     //初始化 请假单状态,请假单状态为未提交
         leave.setStatus(LeaveEmnu.LEAVE_STATUS_UNPOST.getCode());
		if (leave.getLeaveId() != null) {

			try {
		         		
				leaveService.update(leave);
				result.put("message", "请假单更新成功");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try {
				leaveService.saveAndStartProcess(leave);
				result.put("message", "请假单保存成功");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return result;
	}
	
	
	@RequestMapping("/delete")
	public @ResponseBody Map<String, Object> delete(Integer leaveId) {
		Map<String, Object> result = new HashMap<>();
		
		try {
			leaveService.deleteById(leaveId);
			result.put("message", "请假单删除成功");
			result.put("code", 1);
		} catch (Exception e) {
	
			e.printStackTrace();
		}
	
		
		return result;
	}

	
	
	
	
	
	
	@RequestMapping("/detail")
	public String detail(String fromId,Model model) {
		
		log.info("fromId{}",fromId);
		
		try {
			Leave leave =leaveService.selectById(Integer.parseInt(fromId));
			User user = userService.selectById(leave.getUserId());
			List<PvmTransition> outcoms=workFlowService.getPvmTransitionByBusinessKey("leave:"+leave.getLeaveId());
			Task task =workFlowService.getTaskByProcessId("leave:"+leave.getLeaveId());
			String taskId=task.getId();
			
			
			List<String> buttons=outcoms.stream().map(x -> {
			
				return (String)x.getProperty("name");
			}).collect(Collectors.toList());
			
			log.info("leaveINfo{}",leave);
			
			model.addAttribute("taskId",taskId);
			model.addAttribute("leave",leave);
			model.addAttribute("user",user);
			model.addAttribute("buttons",buttons);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "/leave/leaveDetail";
	}
	
	
	
	

	@RequestMapping("/completeTask")
	public @ResponseBody Map<String, Object> completeTask(int leaveId) {
		Map<String, Object> result = new HashMap<>();
		
		try {
			result=leaveService.completeTask(leaveId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	@RequestMapping("/completeTaskByTaskId")
	public @ResponseBody Map<String,Object> completeTask(String taskId,String button){
		
		log.info("taskId:{}",taskId);
		log.info("button:{}",button);
		
		Map<String,Object> variables = new HashMap<>();
		Map<String,Object> result = new HashMap<>();
		
		
		
		variables.put("button", button);
		
	    
		
		
		result.put("message", "任务提交完成");
		try {
			workFlowService.completeTask(taskId, variables);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	
	
	
	
	

}
