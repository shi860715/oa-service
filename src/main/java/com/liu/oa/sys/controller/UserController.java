package com.liu.oa.sys.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liu.oa.common.utils.ResultUtils;
import com.liu.oa.framwork.vo.ResultVO;
import com.liu.oa.sys.form.UserForm;
import com.liu.oa.sys.model.User;
import com.liu.oa.sys.service.UserService;

@Controller
@RequestMapping("sys/user")
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping("/create")
	@ResponseBody
	public ResultVO create(UserForm user) {
		Map<String, Object> result =new HashMap<>();
		User u =userService.create(user);
		result.put("id", u.getUserId());
	    
		return ResultUtils.success("用户创建成功！",result);
	}
	
	
	@GetMapping("/userManager")
	public String user() {
		
		return "/user/user_manager";
		
	}
}
