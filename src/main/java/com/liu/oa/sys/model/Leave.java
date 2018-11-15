package com.liu.oa.sys.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Leave {
	
	private Integer leaveId;
	
	private Integer userId;
	
	private String userName;
	
	private String reson;
	
	private Integer type;
	
	private String remark;
	
	private Integer status;
	
	
	private Double days;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date startTime;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date endTime;
	
	
	private Date createTime;
	
	private Date updateTime;
	
	private String processId;
	
	private Integer flag;

	public Leave(Integer leaveId, Integer userId, String userName, String reson, Integer type, String remark,
			Integer status, Double days, Date startTime, Date endTime, Date createTime, Date updateTime,
			String processId, Integer flag) {
		super();
		this.leaveId = leaveId;
		this.userId = userId;
		this.userName = userName;
		this.reson = reson;
		this.type = type;
		this.remark = remark;
		this.status = status;
		this.days = days;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.processId = processId;
		this.flag = flag;
	}

	public Leave() {
		super();
	}
	
	
	
	
	

}
