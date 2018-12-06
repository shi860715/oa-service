package com.liu.oa.sys.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liu.oa.common.ApplicationContextHandler;
import com.liu.oa.common.enums.WorkFlowEmnu;
import com.liu.oa.sys.service.AwayService;

@Component
public class AwayUserListener implements TaskListener {
	
	
	@Autowired
	private AwayService awayService;

	@Override
	public void notify(DelegateTask delegateTask) {
		if(awayService==null) {
			awayService =ApplicationContextHandler.getBean("awayService");
		}
		
		String button =(String) delegateTask.getVariable("button");
		String businessKey =(String) delegateTask.getVariable("businessKey");
		
		
		String  eventName =delegateTask.getEventName();
		if(eventName.equals("complete")) {
			
				try {
					awayService.updatestatus(businessKey, WorkFlowEmnu.STATUS_WATING.getCode());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}

}
