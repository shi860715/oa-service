package com.liu.oa.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liu.oa.common.utils.ResultUtils;
import com.liu.oa.framwork.vo.ResultVO;
import com.liu.oa.sys.form.UserForm;
import com.liu.oa.sys.service.UserService;

@RestController
@RequestMapping("sys/user")
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping("/create")
	public ResultVO create(UserForm user) {
		
		userService.create(user);
		
		return ResultUtils.success("用户创建成功！");
		
		
	}
	
	
	
}
