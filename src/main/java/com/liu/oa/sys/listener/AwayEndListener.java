package com.liu.oa.sys.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liu.oa.common.ApplicationContextHandler;
import com.liu.oa.common.enums.WorkFlowEmnu;
import com.liu.oa.sys.service.AwayService;

@Component
public class AwayEndListener implements ExecutionListener {
   
	@Autowired
	private AwayService awayService;
	

	@Override
	public void notify(DelegateExecution execution) throws Exception {

		if(awayService== null) {
			awayService =ApplicationContextHandler.getBean("awayService");
		}
		
		String eventName =execution.getEventName();
		
		String businessKey = (String) execution.getVariable("businessKey");
		
		
		if(eventName.equals("end")) {
			
			awayService.updatestatus(businessKey, WorkFlowEmnu.STATUS_SUCCESS.getCode());
			
			
		}
		
	}

}
