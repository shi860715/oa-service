package com.liu.oa.sys.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liu.oa.common.ApplicationContextHandler;
import com.liu.oa.common.enums.WorkFlowEmnu;
import com.liu.oa.sys.service.CostPayService;

@Component
public class CostPayUserListener implements TaskListener {
	
	@Autowired
	private CostPayService costPayService; 

	@Override
	public void notify(DelegateTask delegateTask) {
		
		
		if(costPayService==null) {
			
			costPayService= ApplicationContextHandler.getBean("costPayService");
		}
		
		String  eventName =delegateTask.getEventName();
		
		String businessKey= (String) delegateTask.getVariable("businessKey");
		
		if(eventName.equals("complete")) {
			
			try {
				costPayService.updatestatus(businessKey, WorkFlowEmnu.STATUS_WATING.getCode());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
	}

}
