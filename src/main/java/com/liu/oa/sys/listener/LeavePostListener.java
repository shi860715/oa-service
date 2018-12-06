package com.liu.oa.sys.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liu.oa.common.ApplicationContextHandler;
import com.liu.oa.common.enums.WorkFlowEmnu;
import com.liu.oa.sys.service.LeaveService;

@Component
public class LeavePostListener implements TaskListener {

	@Autowired
	private LeaveService leaveService;
	
	@Override
	public void notify(DelegateTask delegateTask) {
		
		
		if(leaveService==null) {
			leaveService = ApplicationContextHandler.getBean("leaveService");
		}
		
		
		String eventName = delegateTask.getEventName();
		String  assignee=(String) delegateTask.getVariable("userId");
		String businessKey = (String) delegateTask.getVariable("businessKey");
		
		if(eventName.equals("create")) {
			delegateTask.setAssignee(assignee);
		}
		
		if(eventName.equals("complete")) {
			try {
				leaveService.updatestatus(businessKey, WorkFlowEmnu.STATUS_WATING.getCode());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	      
		
	}

}
