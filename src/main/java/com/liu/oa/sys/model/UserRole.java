package com.liu.oa.sys.model;

import java.util.Date;

import com.liu.oa.sys.model.Away.AwayBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
	
	private Integer userId;
	
	private Integer roleId;


}
