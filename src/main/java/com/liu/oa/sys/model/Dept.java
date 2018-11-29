package com.liu.oa.sys.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dept implements Serializable {

	private Integer deptId;

	private String name;

	private Integer level;

	private Integer parentId;

	private Integer sort;

	private Integer manager;

	private String userName;

	private String remark;

	private Date createTime;

	private Date updateTime;

	private Integer flag;

	private Integer _parentId;

	public Integer get_parentId() {

		return this.getParentId();

	}

}
