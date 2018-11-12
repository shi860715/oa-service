package com.liu.oa.sys.mapper;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liu.oa.BaseTest;
import com.liu.oa.common.LeaveEmnu;
import com.liu.oa.framwork.utils.JacksonUtil;
import com.liu.oa.sys.model.Leave;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LeaveMapperTest extends BaseTest{
	
	@Autowired
	LeaveMapper leaveMapper;

	
	
	@Test
	public void update() {
		
		Leave leave =leaveMapper.selectById(1);
		
		leave.setProcessId("1211");
		
		leaveMapper.update(leave);
				
	}
	
	@Test
	public void delById() {
		
	
		leaveMapper.deleteById(1);
				
	}
	
	@Test
	public void findAll() {
		List<Leave> leaves =leaveMapper.findAll();
		
		for (Leave leave : leaves) {
			log.info("查询所有{}",JacksonUtil.printJson(leave));
		}
		
	}
	

}
