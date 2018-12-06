package com.liu.oa.sys.mapper;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.classmate.util.ResolvedTypeCache.Key;
import com.liu.oa.BaseTest;
import com.liu.oa.framwork.model.ShaConfig;
import com.liu.oa.framwork.utils.EncryptUtil;
import com.liu.oa.framwork.utils.KeyUtil;
import com.liu.oa.sys.model.User;

import lombok.extern.slf4j.Slf4j;



@Slf4j
public class UserMapperTest extends BaseTest {
	
	@Autowired
	 UserMapper userMapper;
	
	@Autowired
	ShaConfig shaConfig;


	
	@Test
	public void selectById() throws Exception {
		User user = userMapper.selectById(1);
		log.info("用户信息：{}",user);
	
	}
	
	
	@Test
	public void insert() throws Exception {
		String password = EncryptUtil.sha1("123456lw".getBytes(),shaConfig.getSalt().getBytes(),shaConfig.getIterations()).toString();
		User user = User.builder().userName("小王").userNo("8801")
				.loginName("admin123").password(password).deptId(1).build();
		 userMapper.insert(user);
		log.info("用户信息：{}",user);
	
	}



}
