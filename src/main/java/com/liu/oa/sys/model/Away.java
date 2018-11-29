package com.liu.oa.sys.model;

import java.io.Serializable;
import java.util.Date;

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

	private Date startTime;

	private Date endTime;

	private String reson;

	private String source;

	private String destation;

	private Double days;
	
	private Integer status;
	
	private String  remark;

	private Date leaveTime;
	
	private Integer flag;
	

}
