package com.liu.oa.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.liu.oa.sys.model.User;
import com.liu.oa.sys.model.UserRole;

@Mapper
public interface UserMapper  extends BaseMapper<User> {
    /**
     * 查询公司人员
     * @param ids
     * @param query
     * @return
     */
	List<User> findUserByConpayId(@Param("id")Integer id,@Param("query") String query);

	/**
     * 查询部门员
     * @param ids
     * @param query
     * @return
     */
	List<User> findUserByDeptId(@Param("id")Integer id,@Param("query") String query);
    /**
     * 更新用户和角色关系
     * @param userRole
     */
	void updateUserRoles(UserRole userRole);

	/**
     * 删除用户和角色关系
     * @param userRole
     */
	void deleteUserRolesByUserId(Integer userId);
	

	
	
   
	

	
	
	
}
