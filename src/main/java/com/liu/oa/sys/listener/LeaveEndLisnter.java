package com.liu.oa.sys.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liu.oa.common.ApplicationContextHandler;
import com.liu.oa.sys.service.LeaveService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LeaveEndLisnter implements ExecutionListener {
	
	@Autowired
	LeaveService leaveService;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		
		if(leaveService==null) {
			
			leaveService=(LeaveService) ApplicationContextHandler.getBean("leaveService");
		}
	   String eventName=	execution.getEventName();
	   String businessKey =(String) execution.getVariable("businessKey");
	   log.info("节点 :   {}      业务主键{}",eventName,businessKey);
	   if("end".equals(eventName)){
		   
		   leaveService.updateLeaveGameover(businessKey);
		   
	   }
		
	}

	
	

}
