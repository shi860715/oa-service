package com.liu.oa.sys.controller;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liu.oa.common.enums.ReslutEmnu;
import com.liu.oa.framwork.utils.JacksonUtil;
import com.liu.oa.framwork.utils.KeyUtil;
import com.liu.oa.framwork.utils.ResultUtils;
import com.liu.oa.framwork.vo.ResultVO;
import com.liu.oa.sys.exception.UserLoginException;
import com.liu.oa.sys.form.UserForm;
import com.liu.oa.sys.form.UserRoles;
import com.liu.oa.sys.model.User;
import com.liu.oa.sys.service.DeptService;
import com.liu.oa.sys.service.RedisService;
import com.liu.oa.sys.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("sys/user")
@Slf4j
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	DeptService deptservice;
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	StringRedisTemplate template;

	@PostMapping("/create")
	@ResponseBody
	public ResultVO create(UserForm user) {
		Map<String, Object> result =new HashMap<>();
		User u =userService.create(user);
		result.put("id", u.getUserId());
	    
		return ResultUtils.success("用户创建成功！",result);
	}
	
	
	
	
	@PostMapping("/users")
	@ResponseBody
    public  Map<String, Object> users(int id,String query,int page,int rows){
	
        Map<String, Object> result = new HashMap<>();
      
      
        	 if(id==1 ) {
            	result =userService.findUserByPage(query,page,rows);
                
            }else {
            	
            	result= userService.findUserByDeptParentId(id,query,page,rows);
              
            }
        return result;
        
    }
	
	@GetMapping("/tousers")
	public String tousers() {
		
		
		
		return "user/userList";
	}
	
	@GetMapping("/toUserPhone")
	public String toUserPhone() {
		
		
		
		return "user/userPhone";
	}
	
	@RequestMapping("/saveORupdate")
	@ResponseBody
	public Map<String, Object> saveORupdate(@RequestBody User user){
		 Map<String, Object> result = new HashMap<>();
		 log.info("用户保存或者更新{}",JacksonUtil.printJson(user));
		
		if(user!=null) {
			if(user.getUserId()!=null){
				
				try {
					userService.update(user);
					result.put("msg", "用户更新成功");
				} catch (Exception e) {
					result.put("msg", "用户更新失败");
				}
			
			}else {
				try {
					userService.save(user);
					result.put("msg", "用户创建成功");
				} catch (Exception e) {
					result.put("msg", "用户创建失败");
				
				}
			}
		}
		
		return result;
	}
	
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(Integer userId){
		 Map<String, Object> result = new HashMap<>();
		 
		 try {
			userService.deleteById(userId);
			result.put("msg", "用户删除成功");
		} catch (Exception e) {
			result.put("msg", "用户删除失败");
		}
		
		return result;
	}
	
	@RequestMapping("/updateUserRoles")
	@ResponseBody
	public Map<String, Object> updateUserRoles(@RequestBody UserRoles userRoles){
		 Map<String, Object> result = new HashMap<>();
		 
		 try {
			userService.updateUserRoles(userRoles);
			result.put("msg", "用户授予角色成功");
		} catch (Exception e) {
			result.put("msg", "用户授予角色失败");
		}
		
		return result;
	}
	
	@RequestMapping("/login")
	public String login(String userNo,String password,Model model,HttpSession session,HttpServletResponse response){
		
		 if(StringUtils.isNotEmpty(userNo) & StringUtils.isNotEmpty(password)) {
			 
			 
			User user =null;
			try {
				user = userService.loginUser(userNo, password);
				String key=KeyUtil.keyUnique();
				session.setAttribute("user", user); 
				Cookie cookie = new Cookie("user", key);
				cookie.setMaxAge(60*60);//代表一个小时
				cookie.setPath("/");
				template.opsForValue().set(key, JacksonUtil.toJSon(user));
			
				
				
				response.addCookie(cookie);
				
			} catch (Exception e) {
				throw new UserLoginException(ReslutEmnu.USER_LOGIN_FAILED);
			}
			
			/* return "redirect:/index";*/
			return "forward:/index";
			 
		 }else {
			throw new UserLoginException(ReslutEmnu.USER_PARAM_ERROR);
		}
	}
	
	@RequestMapping("/getUserPhone")
	@ResponseBody
	public Map<String,Object> getUserPhone(int page,int rows,String query){
		 Map<String, Object> result = new HashMap<>();
		 
		 
		 result= userService.getUserPhone(page,rows,query);
		
		
		
		return result;
		
	}
	
	
	
	
	
	
}
