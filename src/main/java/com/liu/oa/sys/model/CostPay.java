package com.liu.oa.sys.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class CostPay implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	private Integer costpayId;// 报销单号

	private Integer userId; //用户

	private String userName;// 用户名称


	private String reson;  //报销理由

	
	private BigDecimal total;// 总金额

	
	private Integer status; //状态
	
	private String  remark;  //备注
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime; // 制单时间
	
	private Integer flag;  // 删除标识
	
	private String processId;// 流程标识
	

}
