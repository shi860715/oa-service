package com.liu.oa.sys.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liu.oa.BaseTest;
import com.liu.oa.framwork.model.ShaConfig;
import com.liu.oa.framwork.utils.EncryptUtil;
import com.liu.oa.sys.model.User;
import com.liu.oa.sys.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImplTest extends BaseTest {

	@Autowired
	UserService userService;
	
	@Autowired
	ShaConfig shaConfig;
	
	
	@Test
   public void addUser() throws Exception {
	    
		String password = EncryptUtil.sha1("123456lw".getBytes(),shaConfig.getSalt().getBytes(),shaConfig.getIterations()).toString();
		User user = User.builder().userName("小王").userNo("8801")
				.loginName("admin123").password(password).deptId(1).build();
		userService.save(user);
	   
		log.info("用户信息：{}",user);
   }

}
