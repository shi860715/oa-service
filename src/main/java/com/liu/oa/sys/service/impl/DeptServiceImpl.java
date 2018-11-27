package com.liu.oa.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liu.oa.framwork.vo.DeptManager;
import com.liu.oa.sys.mapper.DeptMapper;
import com.liu.oa.sys.model.Dept;
import com.liu.oa.sys.service.DeptService;

@Service("deptService")
public class DeptServiceImpl extends BaseServiceImpl<Dept> implements DeptService{
	
	@Autowired
	DeptMapper deptMapper;

	@Override
	@Transactional
	public void updateDeptManager(DeptManager deptManager) {
		
		Dept dept =deptMapper.selectById(deptManager.getDeptId());
		dept.setManager(deptManager.getUserId());
		deptMapper.update(dept);
		
	}


	
	
	
	

}
