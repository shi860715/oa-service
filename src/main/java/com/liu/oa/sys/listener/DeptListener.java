package com.liu.oa.sys.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class DeptListener implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		
		
//		 设置部门经理审批，我的经理是23号
		delegateTask.setAssignee("23");
		
	}

}
