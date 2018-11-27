package com.liu.oa.sys.listener;

import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import com.liu.oa.sys.model.Dept;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ManagerListener implements TaskListener {
	
	

	

	@Override
	public void notify(DelegateTask delegateTask) {
		
		
//		 副总审批，副总徐一方 30
//		赵振、王胜、连赵生
		
		String assignee="";
		
	
		Map<String,Object> variables =delegateTask.getVariables();
		
		
		
		Dept dept =(Dept) variables.get("dept");
		
		
	
		
		switch (dept.getParentId()) {
		case 4:
			assignee="23";
			
			break;
		case 5:
			assignee="34";
			break;
		
		}
		
		delegateTask.setAssignee(assignee);
		
		
		
		
	}

}
