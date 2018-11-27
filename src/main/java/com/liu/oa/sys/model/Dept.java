package com.liu.oa.sys.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Dept implements Serializable{
	
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
	

	

	public Dept() {
		super();
		// TODO Auto-generated constructor stub
	}




	

	public Integer get_parentId() {
		
		return this.getParentId();
		
	}






	public Dept(Integer deptId, String name, Integer level, Integer parentId, Integer sort, Integer manager,
			String userName, String remark, Date createTime, Date updateTime, Integer flag, Integer _parentId) {
		super();
		this.deptId = deptId;
		this.name = name;
		this.level = level;
		this.parentId = parentId;
		this.sort = sort;
		this.manager = manager;
		this.userName = userName;
		this.remark = remark;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.flag = flag;
		this._parentId = _parentId;
	}
	
	

}
