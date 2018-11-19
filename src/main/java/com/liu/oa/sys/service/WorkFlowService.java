package com.liu.oa.sys.service;

import java.io.InputStream;
import java.util.Map;

import org.activiti.engine.repository.Deployment;

public interface WorkFlowService {
	
	
	/**
	 * 分页查找流程，且流程名称
	 * @param page
	 * @param rows
	 * @param query
	 * @return
	 */
	public Map<String,Object> getProcessDefinitions(int page ,int rows,String query) throws Exception;

	public Deployment addDeploy(String fileName, InputStream inputStream)throws Exception; 

}
