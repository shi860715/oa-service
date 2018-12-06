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
import com.liu.oa.sys.mapper.CostPayMapper;
import com.liu.oa.sys.model.CostPay;
import com.liu.oa.sys.model.User;
import com.liu.oa.sys.service.CostPayService;
import com.liu.oa.sys.service.WorkFlowService;

import lombok.extern.slf4j.Slf4j;

@Service("costPayService")
@Slf4j
public class CostPayServiceImpl extends BaseServiceImpl<CostPay> implements CostPayService{
	
	@Autowired
	private CostPayMapper costPayMapper;
	
	@Autowired
	private WorkFlowService workFlowService;

	@Transactional
	public void saveAndProcess(CostPay costPay) throws Exception {
		
		User  user =RequestHolder.getCurrentUser();
		
		// 初始化时间和删除标识符
		costPay.setCreateTime(new Date());
		costPay.setFlag(0);
		
		
		//绑定用户和用户名
		costPay.setUserId(user.getUserId());
		costPay.setUserName(user.getUserName());
		
		 log.info("测试报销单：{}",costPay);
		costPayMapper.insert(costPay);
		Integer costPayId = costPay.getCostpayId();
		String businessKey="costpay:"+costPayId;
		String processDefinitionKey="costpay";
		
		
		
		Map<String,Object> variables = new HashMap<>();
		// 绑定用户
		variables.put("userId", user.getUserId());
		variables.put("businessKey", businessKey);
		
		
		ProcessInstance process = workFlowService.startProcessBybusinessKey(processDefinitionKey, businessKey, variables);
		costPay.setProcessId(process.getId());
		costPayMapper.update(costPay);
		
	}

	
	
	
	
	
	
}
