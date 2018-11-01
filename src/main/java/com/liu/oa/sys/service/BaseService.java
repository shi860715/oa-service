package com.liu.oa.sys.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T> {
	
	T save(T t) throws Exception;
	
	T selectById(Serializable id) throws Exception;
	
	
	List<T> findAll() throws Exception;
	
	
	boolean deleteById(Serializable id) throws Exception;

	void update(T t) throws Exception;
	
}
