package com.liu.oa.sys.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liu.oa.BaseTest;
import com.liu.oa.framwork.utils.JacksonUtil;
import com.liu.oa.sys.model.Dept;
import com.liu.oa.sys.service.DeptService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeptServiceImplTest extends BaseTest{
	
	@Autowired
	DeptService deptService;

	

	@Test
	public void add() throws Exception {
		 Dept dept = Dept.builder().name("市场部").level(2).parentId(5).sort(20).remark("部门").build();
		 Dept save = deptService.save(dept);
		 
		 log.info("保存部门测试{}",JacksonUtil.printJson(save));
	}

}
