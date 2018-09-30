package com.liu.oa.framwork.utils;

import com.github.pagehelper.PageInfo;
import com.liu.oa.framwork.vo.GridVo;

public class GridUtils {
	
	
	public static GridVo getGrid(PageInfo pageInfo) {
		
		return  GridVo.builder().total(pageInfo.getTotal()).rows(pageInfo.getList()).build();
		
	}
	

}
