package com.liu.oa.sys.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleMenu {
	
	private Integer roleId;
	
	private Integer menuId;

	public RoleMenu(Integer roleId, Integer menuId) {
		super();
		this.roleId = roleId;
		this.menuId = menuId;
	}

	public RoleMenu() {
		super();
		
	}
	
	

}
