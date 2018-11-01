package com.liu.oa.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.liu.oa.sys.model.Dept;

@Mapper
public interface DeptMapper extends BaseMapper<Dept> {

	List<Integer> findIdByParentId(Integer id);

	
	
	
	
	
	
	
	

}
