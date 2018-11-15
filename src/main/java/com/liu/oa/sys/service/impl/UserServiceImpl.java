package com.liu.oa.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liu.oa.common.enums.ReslutEmnu;
import com.liu.oa.framwork.utils.Encrypt;
import com.liu.oa.sys.exception.UserException;
import com.liu.oa.sys.exception.UserLoginException;
import com.liu.oa.sys.form.UserForm;
import com.liu.oa.sys.form.UserRoles;
import com.liu.oa.sys.mapper.DeptMapper;
import com.liu.oa.sys.mapper.RoleMapper;
import com.liu.oa.sys.mapper.UserMapper;
import com.liu.oa.sys.model.Dept;
import com.liu.oa.sys.model.Role;
import com.liu.oa.sys.model.User;
import com.liu.oa.sys.model.UserRole;
import com.liu.oa.sys.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service("userService")
@Slf4j
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
	
	public static final String salt ="456852a";
	
	
	private static final int iterations=1026;
	

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	DeptMapper deptMapper;
	
	@Autowired
	RoleMapper roleMapper;
	

	
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
	public  Map<String, Object> findUserByPage(String query, int page, int rows) {
		Map<String, Object> result = new HashMap<>();
		 PageHelper.startPage(page, rows);
		
		 List<User> users =userMapper.findAll(query);
		 for(User u :users) {
			List<Role> roles = roleMapper.findRoleByUserId(u.getUserId());
			u.setRoles(roles);
		 }
		
		 PageInfo<User>   usersInfo = new PageInfo<>(users); 
		 result.put("total", usersInfo.getTotal());
		 result.put("rows", usersInfo.getList());
		
		
		return result;
	}

	@Override
	public Map<String, Object> findUserByDeptParentId(Integer id, String query, Integer page, Integer rows) {
		  Map<String, Object> result = new HashMap<>();
		  Dept dept = deptMapper.selectById(id);
		  List<User> users = new ArrayList<>();
	      PageHelper.startPage(page, rows);
		
	      if(dept.getLevel()==1) {
	         users =userMapper.findUserByConpayId(id,query);
	      }else {
	    	 users =  userMapper.findUserByDeptId(id, query);
	      }
	      
	      for(User u :users) {
				List<Role> roles = roleMapper.findRoleByUserId(u.getUserId());
				u.setRoles(roles);
			 }
	      
	      PageInfo<User>   usersInfo = new PageInfo<>(users); 
	         result.put("total", usersInfo.getTotal());
			 result.put("rows", usersInfo.getList()); 
	      
	
	    
		return result;
	}

	@Override
	public void updateUserRoles(UserRoles userRoles) throws Exception {
		
		 // 删除用户和角色之间的关系
		  userMapper.deleteUserRolesByUserId(userRoles.getUserId());
		
		for (Integer id : userRoles.getRoles()) {
			UserRole userRole = new UserRole();
			userRole.setUserId(userRoles.getUserId());
			userRole.setRoleId(id);
			userMapper.updateUserRoles(userRole);
		}
	}

	@Override
	public User loginUser(String userNo, String password) throws Exception {
		 
		 User user =userMapper.findUserByUserNo(userNo);
		 if(user!=null) {
			 log.info("加密后的密码{}",Encrypt.md5AndSha(password));
		     log.info("用户的密码{}",user.getPassword());
				if( user.getPassword().equals(Encrypt.md5AndSha(password))) {
				     
					
				return user;
					
				}else {
					throw new UserLoginException(ReslutEmnu.USER_LOGIN_FAILED);
					
				}
		 }else {
			 
			throw new UserLoginException(ReslutEmnu.USER_NOT_EXSIT);
			 
		 }
		
	}

	@Override
	public Map<String, Object> getUserPhone(int page, int rows, String query) {
		 Map<String, Object> result = new HashMap<>();
		 PageHelper.startPage(page, rows);
		 List<User> users =userMapper.findAll(query);
		 
		 PageInfo<User> userInfo = new PageInfo<>(users);
		 result.put("total", userInfo.getTotal());
		 result.put("rows", userInfo.getList());
		 
		 
		 
		 
		return result;
	}

	

}
