package com.liu.oa.sys.service;

import java.util.List;

import com.liu.oa.sys.model.Menu;

public interface MenuService extends BaseService<Menu>{

	List<Menu> findMenuByUserId() throws Exception;

}
