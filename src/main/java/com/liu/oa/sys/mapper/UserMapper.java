package com.liu.oa.sys.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.liu.oa.sys.model.User;

@Mapper
public interface UserMapper  extends BaseMapper<User> {
	
    /*
	@InsertProvider(type=UserDymaicProivder.class,method="addUser")
	int addUser(User user) ;
	*/
	

	
	
	
}
