package com.liu.oa.sys.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.liu.oa.common.utils.ResultUtils;
import com.liu.oa.framwork.vo.ResultVO;
import com.liu.oa.sys.form.UserForm;
import com.liu.oa.sys.model.User;
import com.liu.oa.sys.service.DeptService;
import com.liu.oa.sys.service.UserService;

@Controller
@RequestMapping("sys/user")
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	DeptService deptservice;

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
    public   Map<String, Object> users(Integer id,String query,Integer page,Integer rows){
	
        Map<String, Object> result = new HashMap<>();
        PageInfo<User> users= null;
        if(id!=null) {
        	 if(id==1) {
            	 users= userService.findUserByPage(query,page,rows);
            }else {
            	
            	 users= userService.findUserByDeptParentId(id,query,page,rows);
            }
        }else {
        	 users= userService.findUserByPage(query,page,rows);
        }
       
     
   
		
        result.put("total", users.getTotal());
        result.put("rows", users.getList());
        
        return result;
        
    }
	
	@GetMapping("/tousers")
	public String tousers() {
		
		
		
		return "user/userList";
	}
	
	@RequestMapping("/saveORupdate")
	@ResponseBody
	public Map<String, Object> saveORupdate(@RequestBody User user){
		 Map<String, Object> result = new HashMap<>();
		
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
	
}
