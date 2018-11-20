package com.liu.oa.sys.service.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.visitor.functions.Concat;
import com.liu.oa.common.contants.Contant;
import com.liu.oa.sys.model.MyProcessDefinition;
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

}
