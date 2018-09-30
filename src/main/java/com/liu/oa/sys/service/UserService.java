package com.liu.oa.sys.service;

import com.github.pagehelper.PageInfo;
import com.liu.oa.sys.exception.UserException;
import com.liu.oa.sys.form.UserForm;
import com.liu.oa.sys.model.User;

public interface UserService extends BaseService<User> {
	
	
	User create(UserForm user ) throws UserException;

	 PageInfo<User>findUserByPage(String query, int page, int rows);

}
