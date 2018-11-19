package com.liu.oa.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		 
	    Map<String, Object> result = new HashMap<>(); 
		 
	   List<ProcessDefinition> definitions= repositoryService
				           .createProcessDefinitionQuery().processDefinitionNameLike(query)
				           .listPage((page*rows)*(page-1), rows);
		
		
	   int total= repositoryService
		           .createProcessDefinitionQuery().processDefinitionNameLike(query)
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
	
	

}
