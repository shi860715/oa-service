package com.liu.oa.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.liu.oa.sys.model.Role;
import com.liu.oa.sys.model.RoleMenu;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

	/**
	 * 
	 * @param rolemenu
	 * 
	 * 用来保存角色和资源的关系集合
	 * 
	 */
	void saveRoleMenu(RoleMenu rolemenu);
    /**
     * 通过角色的id 返回资源id的集合
     * @param roleId
     * @return
     */
	List<Integer> getMenuIdByRoleId(Integer roleId);
    /**
     * 根据角色的id 来清除角色和资源之间的对应关系
     * @param roleId
     */
	void deleteRoleMenuByRoleId(Integer roleId);
	
	
	/**
	 * 
	 * @param userId
	 */
	List<Role> findRoleByUserId(Integer userId);



	
	
	
}
