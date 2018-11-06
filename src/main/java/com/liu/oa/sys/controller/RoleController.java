package com.liu.oa.sys.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liu.oa.framwork.utils.JacksonUtil;
import com.liu.oa.sys.form.RoleMenus;
import com.liu.oa.sys.model.Role;
import com.liu.oa.sys.service.RoleService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/sys/role")
@Slf4j
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
	
	
	@RequestMapping("/saveORupdate")
	@ResponseBody
	public Map<String, Object>  saveORupdate(@RequestBody Role role){
		Map<String, Object> result = new HashMap<>();
		
		log.info("角色的更新和保存：{}",JacksonUtil.printJson(role));
		if(role!=null) {
			if(role.getRoleId()!=null) {
				
				try {
					roleService.update(role);
					result.put("message", "角色更新成功");
					
				} catch (Exception e) {
					result.put("message", "角色更新失败");
				}
				
			}else {
				try {
					roleService.save(role);
					result.put("message", "角色添加成功");
					
				} catch (Exception e) {
					result.put("message", "角色添加失败");
				}
			}
			
		}else {
			result.put("message", "参数有问题请重新提交");
		}
		
		return result;
	}
	
	
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> deleteRole(Integer roleId){
		
		Map<String, Object> result = new HashMap<>();
		log.info("角色的删除：{}",roleId);
		try {
			roleService.deleteById(roleId);
			
			result.put("message", "角色删除成功");
			result.put("code", 1);
		} catch (Exception e) {
			
			result.put("message", "角色删除失败");
			result.put("code", 0);
		}
		return result;
	}
	
	
	
	@RequestMapping("/updateRoleMenu")
	@ResponseBody
	public Map<String, Object> updateRoleMenu(@RequestBody RoleMenus role){
		Map<String, Object> result = new HashMap<>();
		log.info("测试角色分配资源{}",JacksonUtil.printJson(role));
		
		roleService.updateRoleMenu(role);
		
		
		return result;
		
	}
	
	
	
	
	
	
	
	
}
