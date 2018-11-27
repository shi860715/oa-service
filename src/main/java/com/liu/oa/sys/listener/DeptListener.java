package com.liu.oa.sys.listener;

import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.liu.oa.sys.model.Dept;

public class DeptListener implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		
		
//		 设置部门经理审批，我的经理是23号
		Map<String,Object> variables =delegateTask.getVariables();
		
		Dept dept = (Dept) variables.get("dept");
		
		
		delegateTask.setAssignee(dept.getManager().toString());
		
	}

}
