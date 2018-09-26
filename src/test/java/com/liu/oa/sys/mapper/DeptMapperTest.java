package com.liu.oa.sys.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liu.oa.BaseTest;
import com.liu.oa.sys.model.Dept;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeptMapperTest extends BaseTest{
	
	
	@Autowired
	DeptMapper deptMapper;

	@Test
	public void testGetDept() {
	Dept dept=	deptMapper.selectById(1);
	
	log.info("查询部门信息为：{}",dept);
	}

}
