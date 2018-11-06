package com.liu.oa.sys.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.liu.oa.sys.model.Role;
import com.liu.oa.sys.model.RoleMenu;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

	void saveRoleMenu(RoleMenu rolemenu);



	
	
	
}
