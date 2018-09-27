package com.liu.oa.sys.service.impl;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liu.oa.framwork.utils.EncryptUtil;
import com.liu.oa.sys.exception.UserException;
import com.liu.oa.sys.form.UserForm;
import com.liu.oa.sys.mapper.UserMapper;
import com.liu.oa.sys.model.User;
import com.liu.oa.sys.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
	
	private String salt ="456852a";
	
	
	private int iterations=1026;
	

	@Autowired
	UserMapper userMapper;
	
	@Override
	public User create(UserForm user) throws UserException {
		
		if(user!=null) {
			User u = EncryptPassword(user);
			
			userMapper.insert(u);
			return u;
		}
		
		return null;
	}
	
    /**
     * 将前台接受到的对象进行转换，设置加密后的密码 
     * @param user
     * @return
     */
	private User EncryptPassword(UserForm user) {
		User u  = new User();
		BeanUtils.copyProperties(user, u);
		String password=EncryptUtil.sha1(u.getPassword().getBytes(), salt.getBytes(), iterations).toString();
		u.setPassword(password);
		return u;
	}

}
