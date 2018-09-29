package com.liu.oa.sys.controller;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.mysql.fabric.xmlrpc.base.Array;

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
	
	
	@GetMapping("/users")
	public String user(Map<String, Object> map) {
		
		List<User> users = new ArrayList<>();
		try {
			users = userService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		map.put("users", users);
		
		return "user/user_manager";
		
	}
	
	
	@RequestMapping("/userTree")
	public String userTree() {
		
	 return "user/userTree";
	}
	
	
	
	
	
	
	
	
}
