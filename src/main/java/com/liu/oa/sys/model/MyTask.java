package com.liu.oa.sys.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MyTask {
	
	
	private String taskId;
	
	private String name;
	
	
	private String executionId;
	
	
	private String processId;
	
	private String processDefId;
	
	private String task_def_key;
	
	private String assignee;
	
	private String definitionName;
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date createTime;
	
	
	
	

}
