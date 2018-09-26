package com.liu.oa.sys.mapper;

import java.io.Serializable;
import java.util.List;

public interface BaseMapper<T> {
	
	void insert (T t);
	
	T selectById (Serializable id);
	
	int deleteById(Serializable id);
	
	List<T> findAll();
	
	int update(T t);
	
	
	

}
