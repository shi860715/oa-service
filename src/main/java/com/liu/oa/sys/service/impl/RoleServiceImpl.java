package com.liu.oa.sys.service.impl;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liu.oa.sys.form.RoleMenus;
import com.liu.oa.sys.mapper.RoleMapper;
import com.liu.oa.sys.model.Role;
import com.liu.oa.sys.model.RoleMenu;
import com.liu.oa.sys.model.User;
import com.liu.oa.sys.service.RoleService;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	@Autowired
	RoleMapper roleMapper;
	
	
	public Map<String, Object> findRoles(String query, int page, int rows) throws Exception {
		
		Map<String, Object> result = new HashMap<>();
		
		 PageHelper.startPage(page, rows);
		List<Role> roles= roleMapper.findAll();
		 PageInfo<Role> roleInfo = new PageInfo<>(roles);
		
		 result.put("total", roleInfo.getTotal());
		 result.put("rows", roleInfo.getList());
		
		
		return result;
	}


	@Override
	public void updateRoleMenu(RoleMenus role) {
		
		for (Integer id : role.getIds()) {
			
			RoleMenu rolemenu = RoleMenu.builder().roleId(role.getRoleId()).menuId(id).build();
			roleMapper.saveRoleMenu(rolemenu);
		}
	}
	
	

}
