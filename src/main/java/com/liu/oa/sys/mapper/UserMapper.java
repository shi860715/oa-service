package com.liu.oa.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.liu.oa.sys.model.User;

@Mapper
public interface UserMapper  extends BaseMapper<User> {
    /**
     * 查询公司人员
     * @param ids
     * @param query
     * @return
     */
	List<User> findUserByConpayId(@Param("id")Integer id,@Param("query") String query);

	/**
     * 查询部门员
     * @param ids
     * @param query
     * @return
     */
	List<User> findUserByDeptId(@Param("id")Integer id,@Param("query") String query);
	

	
	
   
	

	
	
	
}
