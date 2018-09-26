package com.liu.oa.sys.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Dict {
	
	
	private Integer dictId;
	
	private String name;
	
	private String type;
	
	private Integer sort;
	
	private String value;
	
	private Integer parentId;
	
	private Integer flag;



	public Dict() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dict(Integer dictId, String name, String type, Integer sort, String value, Integer parentId, Integer flag) {
		super();
		this.dictId = dictId;
		this.name = name;
		this.type = type;
		this.sort = sort;
		this.value = value;
		this.parentId = parentId;
		this.flag = flag;
	}
	
	
	

}
