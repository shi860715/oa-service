package com.liu.oa.sys.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

	public Integer get_parentId() {
		return parentId;
	}

}
