package com.liu.oa.sys.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liu.oa.sys.service.RoleService;

@Controller
@RequestMapping("/sys/role")
public class RoleController {
	
	@Autowired
	 RoleService roleService;
	
	@RequestMapping("/toroles")
	public String toRole(){
		
		
		return "role/roleList";
	}

	@RequestMapping("/roles")
	@ResponseBody
	public Map<String, Object> roles(String query,int rows,int page){
		Map<String, Object> result = new HashMap<>();
		
		try {
			result =roleService.findRoles(query,page,rows);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
		
		
		return result;
	}
	
	
	
	
}
