package com.liu.oa.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liu.oa.sys.mapper.LeaveMapper;
import com.liu.oa.sys.model.Leave;
import com.liu.oa.sys.service.LeaveService;

@Service("leaveService")
public class LeaveServiceImpl extends BaseServiceImpl<Leave> implements LeaveService{
	
	@Autowired
	private LeaveMapper leaveMapper;

	@Override
	public Map<String, Object> findAllByUserId(Integer userId ,String query,int page, int rows) throws Exception {
		
		Map<String, Object> result = new HashMap<>();
		List<Leave> leaves = new ArrayList<>();
		
		
			PageHelper.startPage(page,rows);
			leaves =leaveMapper.findLeaveByUserId(userId,query);
			
			PageInfo<Leave> pageInfo = new PageInfo<>(leaves);
			
			result.put("total", pageInfo.getTotal());
			result.put("rows", pageInfo.getList());
	
		return result;
	}
	
	
	

}
