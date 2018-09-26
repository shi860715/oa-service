package com.liu.oa.sys.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Dept {
	
	private Integer DeptId;
	
	private String name;
	
	private Integer level;
	
	private Integer parentId;
	
	private Integer sort;
	
	private Integer manager;
	
	private String remark;
	
	private Date createTime;
	
	private Date updateTime;
	
	private Integer flag;
	

	public Dept(Integer deptId, String name, Integer level, Integer parentId, Integer sort, Integer manager,
			String remark, Date createTime, Date updateTime, Integer flag) {
		super();
		DeptId = deptId;
		this.name = name;
		this.level = level;
		this.parentId = parentId;
		this.sort = sort;
		this.manager = manager;
		this.remark = remark;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.flag = flag;
	}

	public Dept() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
