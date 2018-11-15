package com.liu.oa.sys.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;




@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class User implements Serializable{
	
	
	private Integer userId;
	
	private String userNo;
	
	private Integer sex;
	
	
	private String userName;
	
	private String loginName;
	
	private String email;
	
	private Integer status;
	
	private String tel;
	
	private String phone;
	
	private String password;
	
	private Integer age;
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date brith;
	
	private String remark;
	
	private Integer flag;
	
	private Date createTime;
	
	private Date updateTime;
	
	
	private Integer deptId;
	
	private String deptName;
	
	private List<Role> roles;


	public User() {
		
	}


	public User(Integer userId, String userNo, Integer sex, String userName, String loginName, String email,
			Integer status, String tel, String phone, String password, Integer age, Date brith, String remark,
			Integer flag, Date createTime, Date updateTime, Integer deptId, String deptName, List<Role> roles) {
		super();
		this.userId = userId;
		this.userNo = userNo;
		this.sex = sex;
		this.userName = userName;
		this.loginName = loginName;
		this.email = email;
		this.status = status;
		this.tel = tel;
		this.phone = phone;
		this.password = password;
		this.age = age;
		this.brith = brith;
		this.remark = remark;
		this.flag = flag;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.deptId = deptId;
		this.deptName = deptName;
		this.roles = roles;
	}


	


	


	

	


	

	
	
	
	

}
