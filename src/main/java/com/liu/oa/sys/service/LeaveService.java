package com.liu.oa.sys.service;

import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;

import com.liu.oa.sys.model.Leave;

public interface LeaveService extends BaseService<Leave> {
	
	
	
	
	/**
	 * 在线登录用户 ，查询自己的请假单，分页查找
	 * @param userId
	 * @param query
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> findAllByUserId(String query,int page, int rows)  throws Exception ;
   
	/**
	 * 启动流程
	 * @param leaveId
	 * @return
	 * @throws Exception
	 */
	void startProcess(int leaveId)throws Exception;
	
	
	/**
	 * 保存请假单，并启动流程
	 * @param leave
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> saveAndStartProcess(Leave leave)throws Exception;
	
     /**
      * 通过请假单ID 提交 任务
      * @param leaveId
      * @return
      * @throws Exception
      */
	Map<String, Object> completeTask(int leaveId)throws Exception;
    
	
	void updateLeaveGameover(String businessKey);
	
	
	
	
	

}
