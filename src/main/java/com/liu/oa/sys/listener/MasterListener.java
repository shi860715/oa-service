package com.liu.oa.sys.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class MasterListener implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		
		
//		董事长，副总赵振 34
		delegateTask.setAssignee("34");
		
	}

}
