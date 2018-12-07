package com.liu.oa.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.liu.oa.sys.model.Menu;

import io.lettuce.core.dynamic.annotation.Param;

@Mapper
public interface MenuMapper extends BaseMapper<Menu>{

	List<Menu> findByType(@Param("type")int type);


	
	
	
}
