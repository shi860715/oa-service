package com.liu.oa.sys.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liu.oa.sys.mapper.AwayMapper;
import com.liu.oa.sys.model.Away;
import com.liu.oa.sys.service.AwayService;
import com.liu.oa.sys.service.WorkFlowService;

@Service("awayService")
public class AwayServiceImpl extends BaseServiceImpl<Away> implements AwayService  {

	@Autowired
	private WorkFlowService workFlowService;
	
	@Autowired
	private AwayMapper awayMapper;
	
	
	@Transactional
	public Map<String,Object> saveAndStartProcess(Away t) throws Exception  {
		Map<String,Object> result = new HashMap<>();
		
		awayMapper.insert(t);
		String businessKey ="away:"+t.getAwayId();
		
		String processDefinitionKey="away";
		
		Map<String, Object> variables = new HashMap<>();
		
		
		ProcessInstance process = workFlowService.startProcessBybusinessKey(processDefinitionKey, businessKey, variables);
		
		t.setProcessId(process.getId());
		
		awayMapper.update(t);
		
		result.put("message","出差申请成功");
		
		
		return result;
	}
	
	
	

}
