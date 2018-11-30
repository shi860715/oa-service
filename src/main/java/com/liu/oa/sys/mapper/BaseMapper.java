package com.liu.oa.sys.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseMapper<T> {
	
	int insert (T t);
	
	T selectById (Serializable id);
	
	int deleteById(Serializable id);
	
	List<T> findAll();
	
	int update(T t);
	
	List<T> findAll(@Param("query") String query);

	List<T> findAllByUserId(@Param("userId") Integer userId, @Param("query")String query);

	void updatestatus(@Param("id") Serializable id,@Param("status") Integer status);
	
	
	

}
