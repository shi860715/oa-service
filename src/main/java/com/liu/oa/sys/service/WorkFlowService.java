package com.liu.oa.sys.service;

import java.util.Map;

public interface WorkFlowService {
	
	
	/**
	 * 分页查找流程，且流程名称
	 * @param page
	 * @param rows
	 * @param query
	 * @return
	 */
	public Map<String,Object> getProcessDefinitions(int page ,int rows,String query) throws Exception; 

}
