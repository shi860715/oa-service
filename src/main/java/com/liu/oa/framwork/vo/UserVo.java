package com.liu.oa.framwork.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class UserVo {
	
    private Integer userId;
	
	private String userNo;
	
	private String userName;
	
	private String loginName;
	
	private Integer sex;
	
	private Integer deptId;

	

}
