package com.liu.oa.framwork.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class TreeNode {

	private Integer id ;
	
	private String text;
	
	private List<TreeNode> children;
	
	private String state;
	
	private boolean checked;
	
	
	private Integer parentId;
	
	private Map<String, Object> attributes;
}
