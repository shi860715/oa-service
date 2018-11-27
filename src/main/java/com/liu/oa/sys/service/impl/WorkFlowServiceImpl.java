package com.liu.oa.sys.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liu.oa.common.contants.Contant;
import com.liu.oa.sys.model.MyProcessDefinition;
import com.liu.oa.sys.model.MyTask;
import com.liu.oa.sys.service.WorkFlowService;

import lombok.extern.slf4j.Slf4j;

@Service("wrokFlowService")
@Slf4j
public class WorkFlowServiceImpl implements WorkFlowService {
	
	@Autowired  
	private RepositoryService repositoryService; 
	@Autowired  
	private RuntimeService runtimeService;  
	@Autowired  
	private TaskService taskService;  


	
	 public Map<String,Object> getProcessDefinitions(int page ,int rows,String query) {
		 String processDefinitionName ="%"+query+"%";
		 
	    Map<String, Object> result = new HashMap<>(); 
		 
	   List<ProcessDefinition> definitions= repositoryService
				           .createProcessDefinitionQuery()
				           .processDefinitionNameLike(processDefinitionName)
				           .processDefinitionKeyLike(processDefinitionName)
				           .listPage((page*rows)*(page-1), rows);
		
		
	   int total= repositoryService
		           .createProcessDefinitionQuery()
		           .processDefinitionNameLike(processDefinitionName)
		           .processDefinitionKeyLike(processDefinitionName)
		            .list().size();
		
	
		
		
		List<MyProcessDefinition> myprocess= definitions.stream().map(d ->{
			MyProcessDefinition def=new MyProcessDefinition();
			        def.setId(d.getId());
			        def.setKey(d.getKey());
			        def.setName(d.getName());
                    def.setVersion(d.getVersion());
                    def.setDeploymentId(d.getDeploymentId());
                    def.setResourceName(d.getResourceName());
			        def.setDiagramResourceName(d.getDiagramResourceName());
			        
			return def;
			
		}).collect(Collectors.toList());
		
		result.put("total", total);
		result.put("rows", myprocess);
		log.info("流程服务类{}",result);
		
		 return result;
		
	 }



	@Override
	public Deployment addDeploy(String fileName, InputStream inputStream) throws Exception {
		 ZipInputStream zip = new ZipInputStream(inputStream);
		 return repositoryService.createDeployment()
		 .addZipInputStream(zip).name(fileName).deploy();
		
		
		
		
		
	}



	@Override
	public Map<String, Object> deleteDefinitionByDeployMentId(String deploymentId) throws Exception {
		Map<String,Object> result = new HashMap<>();
		
		try {
			repositoryService.deleteDeployment(deploymentId);
			result.put("code", Contant.SUCCESS.getCode());
			result.put("message","流程删除成功");
		}catch (Exception e) {
			result.put("code", Contant.FAILED.getCode());
			result.put("message","删除失败，有相关的流程正在运行！如果需要强制删除，请联系管理员");
		}
		return result;
	}



	@Override
	public InputStream getPicByDeploymentId(String deploymentId) throws Exception {
		
	
		String resourceName=getPicNameByDeploynmentId(deploymentId);
		
		InputStream in =   repositoryService.getResourceAsStream(deploymentId, resourceName);
		
		return in;
	}



	@Override
	public String getPicNameByDeploynmentId(String deploymentId) throws Exception {
		List<String> list =repositoryService.getDeploymentResourceNames(deploymentId);
	    String resourceName="";
   
	    if(list!=null&&list.size()>0) {
	    	for(String name : list) {
	    		if(name.indexOf(".png")>0) {
	    			resourceName=name;
	    		}
	    	}
	    }
		return resourceName;
	}



	@Override
	public InputStream getFileByDeploymentId(String deploymentId) throws Exception {
		String resourceName=getFileNameByDeploynmentId(deploymentId);
		
		InputStream in =   repositoryService.getResourceAsStream(deploymentId, resourceName);
		return in;
	}
	
	@Override
	public String getFileNameByDeploynmentId(String deploymentId) throws Exception {
		List<String> list =repositoryService.getDeploymentResourceNames(deploymentId);
	    String resourceName="";
   
	    if(list!=null&&list.size()>0) {
	    	for(String name : list) {
	    		if(name.indexOf(".bpmn")>0) {
	    			resourceName=name;
	    		}
	    	}
	    }
		return resourceName;
	}



	@Override
	public ProcessInstance startProcessByDefinitionKey(String processDefinitionkey, Map<String, Object> variables)
			throws Exception {
		return runtimeService.startProcessInstanceByKey(processDefinitionkey, variables);
	}



	@Override
	public ProcessInstance startProcessByDefinitionId(String processDefinitionId, Map<String, Object> variables)
			throws Exception {
		return runtimeService.startProcessInstanceById(processDefinitionId, variables);
	}



	@Override
	public ProcessInstance startProcessBybusinessKey(String processDefinitionKey,String businessKey, Map<String, Object> variables) throws Exception {
		
		return runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
	}



	@Override
	public void deleteProcessInstance(String processInstanceId, String message) throws Exception {
		
		
		   runtimeService.deleteProcessInstance(processInstanceId, message);
		
	}



	@Override
	public Map<String,Object> getTaskList(String userId,int page,int rows,String query) throws Exception {
		 Map<String, Object> result = new HashMap<>();
		 List<Task> tasks = new ArrayList<Task>();
		
		String queryName="%"+query+"%";
		tasks =taskService.createTaskQuery()
				              .processDefinitionKeyLike(queryName)
				              .processDefinitionNameLike(queryName)
				              .taskNameLike(queryName)
				              .taskAssignee(userId)
				              .listPage((page-1)*rows, rows);
		
		Integer total =taskService.createTaskQuery()
	              .processDefinitionKeyLike(queryName)
	              .processDefinitionNameLike(queryName)
	              .taskNameLike(queryName)
	              .taskAssignee(userId).list().size();
		
		List<MyTask> myTasks=tasks.stream().map(t ->{
			
			MyTask mytask = new MyTask();
			mytask.setTaskId(t.getId());
			mytask.setName(t.getName());
			mytask.setProcessDefId(t.getProcessDefinitionId());
			mytask.setExecutionId(t.getExecutionId());
            mytask.setProcessId(t.getProcessInstanceId());
            mytask.setAssignee(t.getAssignee());
            mytask.setTask_def_key(t.getTaskDefinitionKey());
            mytask.setCreateTime(t.getCreateTime());
            
           ProcessDefinition definition= getProcessDefinitionByID(t.getProcessDefinitionId());
           mytask.setDefinitionName(definition.getName()); 
			
			
			return mytask;
			
			
		}).collect(Collectors.toList());
		
		
		
		result.put("total", total);
		result.put("rows", myTasks);
		return result;
	}



	@Override
	public Task getTaskById(String taskId) throws Exception {
		
	return taskService.createTaskQuery().taskId(taskId).singleResult();  
		
		
	}



	@Override
	public void completeTask(String taskId, Map<String, Object> variables) throws Exception {
	  Double	days =(Double) taskService.getVariable(taskId, "days");
	  
	    variables.put("days", days);
		log.info("variables{}",variables);
		taskService.complete(taskId, variables);
		
		
	}

	
	
	public ProcessDefinition getProcessDefinitionByID(String processDefinitionId) {
		return repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
	}



	@Override
	public ProcessInstance getProcessInstanceByID(String processInstanceId) {
		return runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
	}



	@Override
	public ExecutionEntity getExecutionByID(String executionId) {
		return (ExecutionEntity) runtimeService.createExecutionQuery().executionId(executionId).singleResult();
	}



	@Override
	public Task getTaskByProcessId(String processInstanceBusinessKey) {
		
		return taskService.createTaskQuery().processInstanceBusinessKey(processInstanceBusinessKey).singleResult();
		
		
		
		
		
	}



	@Override
	public void completeTask(String taskId) throws Exception {
		  taskService.complete(taskId);
		
	}



	@Override
	public List<PvmTransition> getPvmTransitionByBusinessKey(String processInstanceBusinessKey) {
		
		// 业务主键获取任务
		Task task = taskService.createTaskQuery().processInstanceBusinessKey(processInstanceBusinessKey).singleResult();
		//任务 获得 流程定义
		ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService)
                .getDeployedProcessDefinition(task.getProcessDefinitionId());
       // 通过任务获得执行实例		
		ExecutionEntity execution = (ExecutionEntity)runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
       // 通过执行实例获得活动任务节点	
		String activitiId =execution.getActivityId();
		//通过流程定义 获得所有的节点
	   List<ActivityImpl> activityImpls =def.getActivities();
	   for (ActivityImpl activityImpl : activityImpls) {
		   if(activitiId.equals(activityImpl.getId())){
			  return activityImpl.getOutgoingTransitions();
		   }
	}
		
		
		return null;
	}



	@Override
	public String getDeployMentIdByProcessIntanceId(String processInstanceId) {
	return runtimeService.createProcessInstanceQuery()
			.processInstanceId(processInstanceId)
			.singleResult()
			.getDeploymentId();
	
		
	}



	@Override
	public ActivityImpl getActivityImpl(String processInstanceId) {
		
		 ProcessInstance process= runtimeService.createProcessInstanceQuery()
			.processInstanceId(processInstanceId)
			.singleResult();
		
		 String processInstanceBusinessKey =process.getBusinessKey();
		Task task= getTaskByProcessId(processInstanceBusinessKey);
		 
		 // 通过任务获得执行实例		
			ExecutionEntity execution = (ExecutionEntity)runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
	       // 通过执行实例获得活动任务节点	
			String activitiId =execution.getActivityId();
		 
		String prodefId= process.getProcessDefinitionId();
		ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService)
                .getDeployedProcessDefinition(prodefId);
		
		//通过流程定义 获得所有的节点
		   List<ActivityImpl> activityImpls =def.getActivities();
		   for (ActivityImpl activityImpl : activityImpls) {
			   if(activitiId.equals(activityImpl.getId())){
				  return activityImpl;
			   }
		}
		   
		   
		   
		return null;
	}
	
	
	
	
	
	
	
}
