package com.liu.oa.sys.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liu.oa.common.ApplicationContextHandler;
import com.liu.oa.common.enums.WorkFlowEmnu;
import com.liu.oa.sys.model.Dept;
import com.liu.oa.sys.service.AwayService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AwayDeptListener implements TaskListener{
	
	@Autowired
	private AwayService awayService;

	@Override
	public void notify(DelegateTask delegateTask) {
		
		if(awayService== null ) {
			awayService =	ApplicationContextHandler.getBean("awayService");
			
		}
		
		String businessKey =(String) delegateTask.getVariable("businessKey");
		Dept dept =(Dept) delegateTask.getVariable("dept");
		
		String eventName =delegateTask.getEventName();
		
	
		
		if(eventName.equals("create")) {
			
			String assignee=dept.getManager().toString();
			delegateTask.setAssignee(assignee);
			
			System.out.println("create............");
			
		}
		if(eventName.equals("complete")) {
			
			try {
				awayService.updatestatus(businessKey,WorkFlowEmnu.STATUS_UNPOST.getCode());
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			System.out.println("complete............");
			
		}
		
		
		
		
	}

}
