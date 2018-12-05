package com.liu.oa.sys.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liu.oa.common.RequestHolder;
import com.liu.oa.sys.mapper.BaseMapper;
import com.liu.oa.sys.model.User;
import com.liu.oa.sys.service.BaseService;
import com.liu.oa.sys.service.WorkFlowService;

public class BaseServiceImpl<T> implements BaseService<T> {
	
	
	@Autowired
	private WorkFlowService workFlowService;

	@Autowired
	private BaseMapper<T> baseMapper;

	@Override
	public T save(T t) {
		int id = baseMapper.insert(t);

		return baseMapper.selectById(id);
	}

	@Override
	public T selectById(Serializable id) {
		return baseMapper.selectById(id);
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return baseMapper.findAll();
	}

	@Override
	public boolean deleteById(Serializable id) {
		boolean flag = false;
		baseMapper.deleteById(id);
		flag = true;
		return flag;
	}

	@Override
	public void update(T t) {
		baseMapper.update(t);

	}

	@Override
	public Map<String, Object> findAllByUserId(int page, int rows, String query) {

		Map<String, Object> result = new HashMap<>();

		User user = RequestHolder.getCurrentUser();

		PageHelper.startPage(page, rows);

		List<T> aways = baseMapper.findAllByUserId(user.getUserId(), query);

		PageInfo<T> pageInfo = new PageInfo<>(aways);

		result.put("total", pageInfo.getTotal());
		result.put("rows", pageInfo.getList());

		return result;
	}

	@Override
	public boolean updatestatus(String businessKey, Integer status){
		boolean flag = false;
		String [] id =businessKey.split(":");
		baseMapper.updatestatus(id[1],status);
		flag=true;
		
		return flag;
	}

	@Override
	public void completeTask(String businessKey, Map<String, Object> variables) throws Exception {
	  Task task=workFlowService.getTaskByBusinessKey(businessKey);
	            String userId =task.getAssignee();
	            if(RequestHolder.getCurrentUser().getUserId().toString().equals(userId)){
	            	  workFlowService.completeTask(task.getId(), variables);
	            }else {
					throw new Exception("您没有权限操作，该任务");
				}
	}

	@Override
	public Map<String, Object> setProcessVariables(String businessKey) {
		Map<String, Object> variables = new HashMap<>();
		
		User user =RequestHolder.getCurrentUser();
		variables.put("userId", user.getUserId());
		variables.put("user", user);	
		variables.put("businessKey", businessKey);
		return variables;
	}

	@Override
	public void startProcess(T t, String businessKey) {
		
		
	}





}
