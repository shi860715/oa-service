package com.liu.oa.sys.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.mail.Flags.Flag;

import org.springframework.beans.factory.annotation.Autowired;

import com.liu.oa.sys.mapper.BaseMapper;
import com.liu.oa.sys.service.BaseService;

public class BaseServiceImpl<T> implements BaseService<T> {
	
	
	
	@Autowired
	BaseMapper<T> baseMapper;

	@Override
	public T save(T t) {
	  int id=	baseMapper.insert(t);
		
		return baseMapper.selectById(id);
	}

	@Override
	public T selectById(Serializable id) {
		return baseMapper.selectById(id);
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return baseMapper.findAll();
	}

	@Override
	public boolean deleteById(Serializable id) {
		 boolean flag = false;
		 baseMapper.deleteById(id);
		 flag =true;
		return flag;
	}

	@Override
	public void update(T t) {
		 baseMapper.update(t);
		
		
	}
	

}
