package com.liu.oa.sys.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liu.oa.BaseTest;
import com.liu.oa.sys.model.Role;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class RoleMapperTest extends BaseTest {

	@Autowired
	RoleMapper rolemapper;
	
	
	@Test
	public void insert() {
		
		Role role = Role.builder().name("超级管理员").remark("可以做任何的事情的超级人类").build();
		rolemapper.insert(role);
		log.info("打印role {}",role);
		
	}
	
	
	@Test
	public void selectById() {
		Role role = rolemapper.selectById(6);
		log.info("打印role {}",role);
		
		
	}
	
	@Test
	public void updateRole() {
		
		Role selectById = rolemapper.selectById(6);
		selectById.setName("超级二号");
		rolemapper.update(selectById);
		
		
		
		
	}
	
	

}
