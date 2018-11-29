package com.liu.oa.sys.model;

import java.util.Date;

import com.liu.oa.sys.model.Away.AwayBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Area { 
	
	private String id;
	
	private String name;
	
	private Integer level;
	
	private String parentId;
	
	private String code;

	
	
	 
	
	
	
	

}
