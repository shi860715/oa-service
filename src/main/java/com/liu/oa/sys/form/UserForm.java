package com.liu.oa.sys.form;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Valid
@Data
public class UserForm {
	
	@NotBlank(message="用户编号不能为")
	private String userNo;
	
	@NotBlank(message="用户姓名不能为空")
	private String userName;

	@NotBlank(message="密码不能为空")
	private String password;
	
	@NotBlank(message="登录名不能为空")
	private String loginName;
	
	
	private String remark;
	@NotBlank(message="请选择部门信息")
	private Integer deptId;
	
	

}
