package com.liu.oa.sys.controller;

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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liu.oa.common.utils.ResultUtils;
import com.liu.oa.framwork.utils.GridUtils;
import com.liu.oa.framwork.vo.GridVo;
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
	
	
	@PostMapping("/users")
	@ResponseBody
    public   Map<String, Object> users(Integer page,Integer rows){
	
        Map<String, Object> result = new HashMap<>();
        String query="";
       PageInfo<User> users= userService.findUserByPage(query,page,rows);
		
        result.put("total", users.getTotal());
        result.put("rows", users.getList());
        
        return result;
        
    }
	
	@GetMapping("/tousers")
	public String tousers() {
		
		System.out.println("=====================");
		
		return "user/userList";
	}
	
	
	
	
	
}
