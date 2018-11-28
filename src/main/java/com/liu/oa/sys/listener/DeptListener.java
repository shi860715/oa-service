package com.liu.oa.sys.listener;

import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liu.oa.common.ApplicationContextHandler;
import com.liu.oa.common.enums.LeaveEmnu;
import com.liu.oa.sys.model.Dept;
import com.liu.oa.sys.service.LeaveService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DeptListener implements TaskListener {
	
	@Autowired
	private LeaveService leaveService;

	@Override
	public void notify(DelegateTask delegateTask) {
		
		if(leaveService==null) {
			leaveService=ApplicationContextHandler.getBean("leaveService");
		}
		String businessKey=(String) delegateTask.getVariable("businessKey");
//		 设置部门经理审批，我的经理是23号
		Map<String,Object> variables =delegateTask.getVariables();
		
		Dept dept = (Dept) variables.get("dept");
		String eventName =delegateTask.getEventName();
		System.out.println("事件名称"+eventName);
		if("create".equals(eventName)) {
			System.out.println("create.................");
			delegateTask.setAssignee(dept.getManager().toString());
			//TODO 可以向用户发送邮件
		}
		if("complete".equals(eventName)) {
			System.out.println("complete.................");
			String button =(String) delegateTask.getVariable("button");
			if(button.equals("驳回")) {
				
				leaveService.updateLeaveStatus(businessKey,LeaveEmnu.LEAVE_STATUS_UNPOST.getCode());
			}
			
			
		}
		
		
		
		
		
		
	}

	
	

}
