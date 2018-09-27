package com.liu.oa.sys.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;




@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class User {
	
	
	private Integer userId;
	
	private String userNo;
	
	
	private String userName;
	
	private String loginName;
	
	private String email;
	
	private String phone;
	
	private String password;
	
	private Integer age;
	
	
	private Date brith;
	
	private String remark;
	
	private Integer flag;
	
	private Date createTime;
	
	private Date updateTime;
	
	
	private Integer deptId;


	public User() {
		
	}


	public User(Integer userId, String userNo, String userName, String loginName, String email, String phone,
			String password, Integer age, Date brith, String remark, Integer flag, Date createTime, Date updateTime,
			Integer deptId) {
		super();
		this.userId = userId;
		this.userNo = userNo;
		this.userName = userName;
		this.loginName = loginName;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.age = age;
		this.brith = brith;
		this.remark = remark;
		this.flag = flag;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.deptId = deptId;
	}


	
	
	
	

}
