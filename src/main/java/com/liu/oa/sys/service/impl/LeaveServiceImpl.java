package com.liu.oa.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liu.oa.common.RequestHolder;
import com.liu.oa.framwork.utils.JacksonUtil;
import com.liu.oa.sys.mapper.LeaveMapper;
import com.liu.oa.sys.model.Leave;
import com.liu.oa.sys.model.User;
import com.liu.oa.sys.service.LeaveService;

import lombok.extern.slf4j.Slf4j;

@Service("leaveService")
@Slf4j
public class LeaveServiceImpl extends BaseServiceImpl<Leave> implements LeaveService{
	
	@Autowired
	private LeaveMapper leaveMapper;

	@Override
	public Map<String, Object> findAllByUserId(String query,int page, int rows) throws Exception {
		
		Map<String, Object> result = new HashMap<>();
		List<Leave> leaves = new ArrayList<>();
		    User user =RequestHolder.getCurrentUser();
		    log.info("请假单用户id{}",JacksonUtil.printJson(user));
		
		    PageHelper.startPage(page, rows);
			leaves =leaveMapper.findLeaveByUserId(user.getUserId(),query);
			
			PageInfo<Leave> pageInfo = new PageInfo<>(leaves);
			
			result.put("total", pageInfo.getTotal());
			result.put("rows", pageInfo.getList());
			 log.info("请假单返回{}",JacksonUtil.printJson(result));
	
		return result;
	}
	
	
	

}
