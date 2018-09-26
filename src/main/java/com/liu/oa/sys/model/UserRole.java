package com.liu.oa.sys.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRole {
	
	private Integer userId;
	
	private Integer roleId;

	public UserRole(Integer userId, Integer roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}

	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	

}
