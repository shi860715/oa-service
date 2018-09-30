package com.liu.oa.framwork.vo;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GridVo<T> {
	
	private Long total;
   
	private List<T> rows;
	
	
	
	
}
