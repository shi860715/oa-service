package com.liu.oa.sys.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Menu {
	
	private Integer menuId;
	
	private String name;
	
	// 1 模块 、2 菜单 、3 按钮
	private Integer type;
	
	private Integer sort;
	
	private Integer parentId;
	
	private String url;
	
	private String icon;
	
	private String target;
	
	private Integer flag;
	
	private Integer _parentId;

	

	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}




	
	public Integer get_parentId() {
		return parentId;
	}





	public Menu(Integer menuId, String name, Integer type, Integer sort, Integer parentId, String url, String icon,
			String target, Integer flag, Integer _parentId) {
		super();
		this.menuId = menuId;
		this.name = name;
		this.type = type;
		this.sort = sort;
		this.parentId = parentId;
		this.url = url;
		this.icon = icon;
		this.target = target;
		this.flag = flag;
		this._parentId = _parentId;
	}

}
