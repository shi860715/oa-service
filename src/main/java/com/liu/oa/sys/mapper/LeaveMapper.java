package com.liu.oa.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.liu.oa.sys.model.Leave;

@Mapper
public interface LeaveMapper extends BaseMapper<Leave>{

	List<Leave> findLeaveByUserId(Integer userId, String query);

}
