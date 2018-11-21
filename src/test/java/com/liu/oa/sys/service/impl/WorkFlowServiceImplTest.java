package com.liu.oa.sys.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liu.oa.BaseTest;
import com.liu.oa.sys.service.WorkFlowService;

public class WorkFlowServiceImplTest extends BaseTest {

	@Autowired
	private WorkFlowService workFlowService;
	
	
	@Autowired  
	private RepositoryService repositoryService; 
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Test
	public void  testAdddeployment() {
		
		File file = new File("E:\\git\\oa-service\\src\\main\\resources\\bpm\\leave.zip");
		FileInputStream fi =null;
		try {
			fi = new FileInputStream(file);
			workFlowService.addDeploy("请假申请", fi);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	@Test
	public void testStartProcessInstace() throws Exception {
		
		String processDefinitionId="leave:11:27504";
		
		
		Map<String, Object> variables = new HashMap<>();
		variables.put("userId", "16");
		
		ProcessInstance process =workFlowService.startProcessByDefinitionId(processDefinitionId, variables);
		System.out.println(process.getId());//20001 25001 30001
	}
	
	
	@Test
	public void testTask() throws Exception {
		String userId ="16";
		int page =1;
		int rows=10;
		List<Task> tasks =workFlowService.getTaskList(userId,page,rows);
		
		System.out.println("任务列表长度"+tasks.size());
		for(Task t: tasks) {
			System.out.println("任务Id："+t.getId());
			System.out.println("任务任务人："+t.getAssignee());
			System.out.println("任务名称："+t.getName());
			System.out.println("任务本地参数："+t.getTaskLocalVariables());
			System.out.println("任务参数:"+t.getProcessVariables());
			
		}
	}
	
	
	@Test	
	public void testTaskById() throws Exception {
		String taskId="30005";
		Task t =workFlowService.getTaskById(taskId);
		System.out.println("任务Id："+t.getId());
		System.out.println("任务任务人："+t.getAssignee());
		System.out.println("任务名称："+t.getName());
		System.out.println("任务本地参数："+t.getTaskLocalVariables());
		System.out.println("任务参数:"+t.getProcessVariables());
	} 	
	
	
	@Test
	public void testActivitiImpl() throws Exception {
		String taskId="30005";
		/*ProcessDefinitionEntity def =*/ 
		Task t =workFlowService.getTaskById(taskId);
		ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService)
				                                          .getDeployedProcessDefinition(t.getProcessDefinitionId());
		List<ActivityImpl> activtiList =def.getActivities();
		 System.out.println("===========获取所有的节点==============");
		 System.out.println("获取所有的节点："+activtiList.size());
		for(ActivityImpl act :activtiList) {
			System.out.println("id："+act.getId());
			System.out.println("高："+act.getHeight());
			System.out.println("宽："+act.getWidth());
			System.out.println("x："+act.getX());
			System.out.println("y："+act.getY());
		}
		
		
		 System.out.println("===========获取任务的当前节点==============");
		
		
		String excId=t.getExecutionId();
		
		 
		ExecutionEntity execution = (ExecutionEntity)runtimeService.createExecutionQuery().executionId(excId).singleResult();
		String activitiId =execution.getActivityId();
		
		
		for(ActivityImpl act :activtiList) {
			if(activitiId.equals(act.getId())) {
				
				System.out.println("当前任务："+act.getProperty("name")); //输出某个节点的某种属性
				
				List<PvmTransition> outTransitions = act.getOutgoingTransitions();//获取从某个节点出来的线路
				List<PvmTransition> incomTransitions = act.getIncomingTransitions();
				
				for(PvmTransition pvm:outTransitions) {
					PvmActivity ac =pvm.getDestination();
					 String button_name =(String)pvm.getProperty("name");
					 String button_id=pvm.getId();
				 
					 System.out.println("按钮名称："+button_name);
					 System.out.println("按钮Id："+button_id);
					System.out.println("下一步任务任务："+ac.getProperty("name"));
					
				}
				
				for(PvmTransition pvm:incomTransitions) {
					PvmActivity ac =pvm.getDestination();
				
					System.out.println("上一步任务任务："+ac.getProperty("name"));
					
				}
				
			}
			
		}
		
		
		
		
	}
	
	
	

}
