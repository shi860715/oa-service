package com.liu.oa.sys.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.liu.oa.sys.exception.UserException;
import com.liu.oa.sys.form.UserForm;
import com.liu.oa.sys.form.UserRoles;
import com.liu.oa.sys.model.User;

public interface UserService extends BaseService<User> {
	
	
	User create(UserForm user ) throws Exception;

	Map<String, Object> findUserByPage(String query, int page, int rows) throws Exception;


	Map<String, Object> findUserByDeptParentId(Integer id, String query, Integer page, Integer rows) throws Exception;

	void updateUserRoles(UserRoles userRoles) throws Exception;

	User loginUser(String userNo, String password)throws Exception;

	Map<String, Object> getUserPhone(int page, int rows, String query) throws Exception;




}
