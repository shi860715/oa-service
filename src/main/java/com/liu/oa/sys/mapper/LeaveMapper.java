package com.liu.oa.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.liu.oa.sys.model.Leave;

@Mapper
public interface LeaveMapper extends BaseMapper<Leave>{

	List<Leave> findLeaveByUserId(@Param("userId")Integer userId,@Param("query") String query);

}
