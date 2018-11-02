package com.liu.oa.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liu.oa.sys.model.Menu;
import com.liu.oa.sys.service.MenuService;

@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService{

	@Autowired
	private MenuService menuService;
	
	
}
