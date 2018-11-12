package com.liu.oa.sys.model;

import java.util.Date;

import lombok.Data;


@Data
public class Leave {
	
	private Integer leaveId;
	
	private Integer userId;
	
	private String userName;
	
	private String reson;
	
	private Integer type;
	
	private String remark;
	
	private Integer status;
	
	
	
	private Double days;
	
	private Date startTime;
	
	private Date endTime;
	
	
	
	private Date createTime;
	
	
	private Date updateTime;
	
	private String processId;
	
	private Integer flag;
	
	
	

}
