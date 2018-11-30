package com.liu.oa.sys.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

public interface BaseService<T> {
	
	T save(T t) throws Exception;
	
	T selectById(Serializable id) throws Exception;
	
	
	List<T> findAll() throws Exception;
	
	
	boolean deleteById(Serializable id) throws Exception;

	void update(T t) throws Exception;
	
	
	public Map<String, Object> findAllByUserId(int page, int rows, String query);
	
	
	
	/**
	 * 通过业务主键 来更新状态
	 * @param businessKey
	 * @param status
	 * @return
	 */
	public boolean updatestatus(String businessKey  ,Integer status) ;
	
	
	/**
	 * 通过业务主键，和流程变量来完成任务
	 * @param businessKey
	 * @param variables
	 */
	public void completeTask(String businessKey,Map<String,Object> variables) throws Exception;
	
	
}
