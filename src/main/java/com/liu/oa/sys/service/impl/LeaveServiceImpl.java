package com.liu.oa.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liu.oa.common.RequestHolder;
import com.liu.oa.common.enums.LeaveEmnu;
import com.liu.oa.framwork.utils.JacksonUtil;
import com.liu.oa.sys.mapper.DeptMapper;
import com.liu.oa.sys.mapper.LeaveMapper;
import com.liu.oa.sys.model.Dept;
import com.liu.oa.sys.model.Leave;
import com.liu.oa.sys.model.User;
import com.liu.oa.sys.service.LeaveService;
import com.liu.oa.sys.service.WorkFlowService;

import lombok.extern.slf4j.Slf4j;

@Service("leaveService")
@Slf4j
public class LeaveServiceImpl extends BaseServiceImpl<Leave> implements LeaveService{
	
	@Autowired
	private LeaveMapper leaveMapper;
	
	@Autowired
	private DeptMapper deptMapper;
	
	@Autowired
	private WorkFlowService workFlowService;

	@Override
	public Map<String, Object> findAllByUserId(String query,int page, int rows) throws Exception {
		
		Map<String, Object> result = new HashMap<>();
		List<Leave> leaves = new ArrayList<>();
		    User user =RequestHolder.getCurrentUser();
		    log.debug("请假单用户id{}",JacksonUtil.printJson(user));
		
		    PageHelper.startPage(page, rows);
			leaves =leaveMapper.findLeaveByUserId(user.getUserId(),query);
			
			PageInfo<Leave> pageInfo = new PageInfo<>(leaves);
			
			result.put("total", pageInfo.getTotal());
			result.put("rows", pageInfo.getList());
			 log.info("请假单返回{}",JacksonUtil.printJson(result));
	
		return result;
	}

	@Override
	public void startProcess(int leaveId) throws Exception {
		String processDefinitionKey = "leave";
		String businessKey="leave:"+leaveId;
		Leave leave =leaveMapper.selectById(leaveId);
		log.info("【启动流程】businessKey={}",businessKey);
		Map<String,Object>  variables = new HashMap<>();
		User user =RequestHolder.getCurrentUser();
		Dept dept = deptMapper.selectById(user.getDeptId());
		
		 variables.put("userId", user.getUserId().toString());
		 variables.put("days",leave.getDays());
		 variables.put("reson",leave.getReson());
		 variables.put("user",user);
		 variables.put("dept",dept);
		 
		
		 
		 
		 
		 
		  ProcessInstance instance =workFlowService.startProcessBybusinessKey(processDefinitionKey, businessKey, variables);
		  if(instance!=null) {
			 
			  leave.setProcessId(instance.getProcessInstanceId());
			  leaveMapper.update(leave);
		  }
		
	}

	
	@Transactional
	public Map<String, Object> saveAndStartProcess(Leave leave) throws Exception {
		Map<String,Object>  result = new HashMap<>();
		User user =RequestHolder.getCurrentUser();
		//初始化 请假单用户信息和请假时间
		leave.setLeaveTime(new Date());
		leave.setUserId(user.getUserId());
		leave.setUserName(user.getUserName());
		
		leaveMapper.insert(leave);
		log.info("请假单ID{}",leave.getLeaveId());
		startProcess(leave.getLeaveId());
		
	    
	
		result.put("message", "请假单保存成功");
		return result;
	}

	@Override
	@Transactional
	public Map<String, Object> completeTask(int leaveId) throws Exception {
		Map<String,Object>  result = new HashMap<>();
		Map<String,Object>  variables = new HashMap<>();
		Leave leave =leaveMapper.selectById(leaveId);
		String processInstanceBusinessKey="leave:"+leave.getLeaveId();
		Task task =workFlowService.getTaskByProcessId(processInstanceBusinessKey);
		variables.put("button", "提交");
		workFlowService.completeTask(task.getId(),variables);
		//更新请假单状态
		leave.setStatus(LeaveEmnu.LEAVE_STATUS_WATING.getCode());
		leaveMapper.update(leave);
		result.put("message", "任务提交成功");
		
		
		return result;
	}
	
	
	

}
