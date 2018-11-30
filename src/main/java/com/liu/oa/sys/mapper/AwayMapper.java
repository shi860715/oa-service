package com.liu.oa.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.liu.oa.sys.model.Away;

@Mapper
public interface AwayMapper extends BaseMapper<Away>{

	List<Away> findAwayByUserId(@Param("userId")Integer userId,@Param("query") String query);

	void updateAwayStatus(@Param("status")int status, @Param("awayId") int awayId);



}
