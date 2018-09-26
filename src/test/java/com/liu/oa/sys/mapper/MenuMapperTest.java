package com.liu.oa.sys.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liu.oa.BaseTest;
import com.liu.oa.sys.model.Menu;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class MenuMapperTest extends BaseTest{
	
	
	@Autowired
	MenuMapper menuMapper;

	@Test
	public void insert() {
		
		Menu build = Menu.builder().name("系统").type(0).sort(10).url("/main").icon("menu").build();
		
		
		menuMapper.insert(build);
		
		log.info("添加的菜单为：{}",build);
		
		
	}

}
