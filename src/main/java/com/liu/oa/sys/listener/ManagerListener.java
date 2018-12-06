package com.liu.oa.sys.listener;

import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liu.oa.common.ApplicationContextHandler;
import com.liu.oa.common.enums.LeaveEmnu;
import com.liu.oa.common.enums.WorkFlowEmnu;
import com.liu.oa.sys.model.Dept;
import com.liu.oa.sys.service.LeaveService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ManagerListener implements TaskListener {
	
	@Autowired
	private LeaveService leaveService;


	@Override
	public void notify(DelegateTask delegateTask) {
		if(leaveService==null) {
			leaveService=ApplicationContextHandler.getBean("leaveService");
		}
		
		String assignee="";
		Dept dept =(Dept) delegateTask.getVariable("dept");
		String businessKey=(String) delegateTask.getVariable("businessKey");
		String eventName =delegateTask.getEventName();
		String button =(String) delegateTask.getVariable("button");
		if("create".equals(eventName)) {
			System.out.println("create.................");
			switch (dept.getParentId()) {
			case 4:
				assignee="23";//76 老连
				break;
			case 5:
				assignee="34";
				break;
			}
			delegateTask.setAssignee(assignee);
		}
		if("complete".equals(eventName)) {
			System.out.println("complete.................");
			
			if(button.equals("驳回")) {
				
				try {
					leaveService.updatestatus(businessKey,WorkFlowEmnu.STATUS_UNPOST.getCode());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		
		
	
		
		
		
		
	}

}
