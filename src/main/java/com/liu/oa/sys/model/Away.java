package com.liu.oa.sys.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Away implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	private Integer awayId;

	private Integer userId;

	private String userName;
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date startTime;
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date endTime;

	private String reson;

	private String source;

	private String destation;

	private Double days;
	
	private Integer status;
	
	private String  remark;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date leaveTime;
	
	private Integer flag;
	
	private String processId;
	

}
