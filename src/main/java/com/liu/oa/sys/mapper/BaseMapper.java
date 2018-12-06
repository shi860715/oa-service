package com.liu.oa.sys.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseMapper<T> {
	
	int insert (T t) throws Exception;
	
	T selectById (Serializable id) throws Exception;
	
	int deleteById(Serializable id) throws Exception;
	
	List<T> findAll() throws Exception;
	
	int update(T t) throws Exception;
	
	List<T> findAll(@Param("query") String query) throws Exception;

	List<T> findAllByUserId(@Param("userId") Integer userId, @Param("query")String query) throws Exception;

	void updatestatus(@Param("id") Serializable id,@Param("status") Integer status) throws Exception;
	
	
	

}
