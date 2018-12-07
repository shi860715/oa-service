package com.liu.oa.sys.model;

import java.io.Serializable;
import java.util.Date;

import com.liu.oa.sys.model.Away.AwayBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable{
	
	
	private Integer roleId;
	private String name;
	private String remark;
	private Integer flag;
	
	private Date createTime;
	
	private Date updateTime;

	

}
