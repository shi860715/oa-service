package com.liu.oa.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liu.oa.common.RequestHolder;
import com.liu.oa.framwork.utils.JacksonUtil;
import com.liu.oa.sys.mapper.MenuMapper;
import com.liu.oa.sys.model.Menu;
import com.liu.oa.sys.model.User;
import com.liu.oa.sys.service.MenuService;

@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService{

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<Menu> findMenuByUserId() throws Exception {
		
		User user = RequestHolder.getCurrentUser();
		
		JacksonUtil.printJson(user);
		
		List<Menu> menu1 = menuMapper.findByType(0);
		
		List<Menu> menus =menuMapper.findAllByUserId(user.getUserId(), null);
		menus.addAll(menu1);
		
		
		
		return menus;
	}
	
	
}
