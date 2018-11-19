package com.liu.oa.sys.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class ManagerListener implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		
		
//		 副总审批，副总徐一方 30
		delegateTask.setAssignee("30");
		
	}

}
