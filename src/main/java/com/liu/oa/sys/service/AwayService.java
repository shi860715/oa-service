package com.liu.oa.sys.service;

import java.util.Map;

import com.liu.oa.sys.model.Away;

public interface AwayService extends BaseService<Away> {
	
	
	public Map<String,Object> saveAndStartProcess(Away t)throws Exception;


	public Map<String, Object> completeTask(String processId);


}
