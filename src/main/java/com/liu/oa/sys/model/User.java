package com.liu.oa.sys.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.liu.oa.sys.model.Away.AwayBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class User implements Serializable {

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

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date brith;

	private String remark;

	private Integer flag;

	private Date createTime;

	private Date updateTime;

	private Integer deptId;

	private String deptName;

	private List<Role> roles;

}
