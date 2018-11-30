package com.liu.oa.sys.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liu.oa.common.RequestHolder;
import com.liu.oa.common.enums.WorkFlowEmnu;
import com.liu.oa.sys.mapper.AwayMapper;
import com.liu.oa.sys.mapper.DeptMapper;
import com.liu.oa.sys.model.Away;
import com.liu.oa.sys.model.Dept;
import com.liu.oa.sys.model.User;
import com.liu.oa.sys.service.AwayService;
import com.liu.oa.sys.service.WorkFlowService;

@Service("awayService")
public class AwayServiceImpl extends BaseServiceImpl<Away> implements AwayService  {

	@Autowired
	private WorkFlowService workFlowService;
	
	@Autowired
	private AwayMapper awayMapper;
	
	@Autowired
	private DeptMapper deptMapper;
	
	
	@Transactional
	public Map<String,Object> saveAndStartProcess(Away t) throws Exception  {
		Map<String,Object> result = new HashMap<>();
		
		User user =RequestHolder.getCurrentUser();
		t.setUserId(user.getUserId());
		t.setUserName(user.getUserName());
		t.setFlag(0);
		t.setLeaveTime(new Date());
		t.setStatus(WorkFlowEmnu.STATUS_UNPOST.getCode());
		
		
		
		
		awayMapper.insert(t);
		
		String businessKey ="away:"+t.getAwayId();
		
		String processDefinitionKey="away";
		
		
		
		
		Map<String, Object> variables = new HashMap<>();
		Dept dept =deptMapper.selectById(user.getDeptId());
		variables.put("businessKey", businessKey);
		variables.put("user", user);
		variables.put("userId", user.getUserId().toString());
		variables.put("dept", dept);
	
		
		
		ProcessInstance process = workFlowService.startProcessBybusinessKey(processDefinitionKey, businessKey, variables);
		
		t.setProcessId(process.getId());
		
		awayMapper.update(t);
		
		result.put("message","出差申请成功");
		
		
		return result;
	}


	@Override
	public void updateAwayStatus(String businessKey, Integer status) throws Exception {
		String [] id = businessKey.split(":");
		
		updatestatus(id[1], status);
	}


	@Override
	public Map<String, Object> completeTask(String processId) {
		
	/*   Task task = workFlowService.getTaskByProcessId(processInstanceBusinessKey)*/
		
		
		
		
		
		return null;
	}


	
	
	
	

}
