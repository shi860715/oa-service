package com.liu.oa.sys.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.liu.oa.sys.model.Away.AwayBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Leave implements Serializable{
	
	private Integer leaveId;
	
	private Integer userId;
	
	private String userName;
	
	private String reson;
	
	private Integer type;
	
	private String remark;
	
	private Integer status;
	
	
	private Double days;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date leaveTime;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date startTime;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date endTime;
	
	
	private Date createTime;
	
	private Date updateTime;
	
	private String processId;
	
	private Integer flag;



	
	
	
	

}
