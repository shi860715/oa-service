package com.liu.oa.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liu.oa.framwork.utils.Encrypt;
import com.liu.oa.sys.exception.UserException;
import com.liu.oa.sys.form.UserForm;
import com.liu.oa.sys.mapper.DeptMapper;
import com.liu.oa.sys.mapper.UserMapper;
import com.liu.oa.sys.model.Dept;
import com.liu.oa.sys.model.User;
import com.liu.oa.sys.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
	
	public static final String salt ="456852a";
	
	
	private static final int iterations=1026;
	

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	DeptMapper deptMapper;
	

	
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
		
		u.setPassword(Encrypt.md5AndSha(user.getPassword()));
		return u;
	}

	/**
	 * 分页查询用户列表
	 */
	public  PageInfo<User> findUserByPage(String query, int page, int rows) {
		       PageHelper.startPage(page, rows);
		
		 List<User> users =userMapper.findAll();
		 PageInfo<User>   usersInfo = new PageInfo<>(users); 
		
		
		
		return usersInfo;
	}

	@Override
	public PageInfo<User> findUserByDeptParentId(Integer id, String query, Integer page, Integer rows) {
	
		
	      PageHelper.startPage(page, rows);
		  Dept dept = deptMapper.selectById(id);
		  List<User> users = new ArrayList<>();
	      if(dept.getLevel()==1) {
	         users =userMapper.findUserByConpayId(id,query);
	      }else {
	    	 users =  userMapper.findUserByDeptId(id, query);
	      }
	      
	      
	      
	
	      PageInfo<User>   usersInfo = new PageInfo<>(users); 
		
		return usersInfo;
	}

	

}
