package com.liu.oa.sys.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class MasterListener implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		
		
//		董事长，裁忠心 88
		delegateTask.setAssignee("88");
		
	}

}
