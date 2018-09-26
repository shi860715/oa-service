package com.liu.oa.sys.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Area { 
	
	private String id;
	
	private String name;
	
	private Integer level;
	
	private String parentId;
	
	private String code;

	public Area(String id, String name, Integer level, String parentId, String code) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.parentId = parentId;
		this.code = code;
	}

	public Area() {
		super();
	
	}
	
	
	 
	
	
	
	

}
