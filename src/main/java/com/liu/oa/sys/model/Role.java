package com.liu.oa.sys.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class Role {
	
	
	private Integer roleId;
	private String name;
	private String remark;
	private Integer flag;
	
	private Date createTime;
	
	private Date updateTime;

	public Role(Integer roleId, String name, String remark, Integer flag, Date createTime, Date updateTime) {
		super();
		this.roleId = roleId;
		this.name = name;
		this.remark = remark;
		this.flag = flag;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
