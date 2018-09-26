package com.liu.oa.sys.sql;

import org.apache.ibatis.jdbc.SQL;

import com.liu.oa.sys.model.User;

public class UserDymaicProivder {
	
	
	
	public String addUser(User user) {
		
		return new SQL() {{
			
			INSERT_INTO("sys_user");
			
			VALUES("user_no", "#{userNo}");
			VALUES("user_name", "#{userName}");
			VALUES("login_name", "#{loginName}");
			VALUES("password", "#{password}");
			
			if(user.getAge()!=null) {
				VALUES("age", "#{age}");
			}
			if(user.getBrith()!=null) {
			    VALUES("brith", "#{brith}");
			}
			if(user.getRemark()!=null) {
			    VALUES("remark", "#{remark}");
			}
			
			VALUES("flag", "1");
		    VALUES("create_time", "#{createTime}");    
			    
			
			
			
		}}.toString();
		
		
		
	}
	

}
