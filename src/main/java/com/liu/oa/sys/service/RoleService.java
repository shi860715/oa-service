package com.liu.oa.sys.service;

import java.util.Map;

import com.liu.oa.sys.form.RoleMenus;
import com.liu.oa.sys.model.Role;

public interface RoleService extends BaseService<Role>{

	Map<String, Object> findRoles(String query, int page, int rows) throws Exception;

	void updateRoleMenu(RoleMenus role);

}
