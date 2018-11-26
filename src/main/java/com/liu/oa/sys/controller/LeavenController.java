package com.liu.oa.sys.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liu.oa.common.RequestHolder;
import com.liu.oa.common.enums.LeaveEmnu;
import com.liu.oa.framwork.utils.JacksonUtil;
import com.liu.oa.sys.model.Leave;
import com.liu.oa.sys.model.User;
import com.liu.oa.sys.service.LeaveService;
import com.liu.oa.sys.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/sys/leave")
@Slf4j
public class LeavenController {

	@Autowired
	private LeaveService leaveService;
	
	@Autowired
	private UserService userService;
	


	@RequestMapping("/toleaves")
	public String toLeaves(Model model) {

		model.addAttribute("user", RequestHolder.getCurrentUser());
		return "/leave/leaveList";
	}

	@RequestMapping("/leavesByUserId")
	public @ResponseBody Map<String, Object> leavesByUserId(String query, int page, int rows) {
		Map<String, Object> result = new HashMap<>();

		log.info("参数：query {} page {} rows {}", query, page, rows);

		try {
			result = leaveService.findAllByUserId(query, page, rows);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		log.info(" 打印请假单{}", JacksonUtil.printJson(result));
		return result;
	}

	@RequestMapping("/saveOrUpdate")
	public @ResponseBody Map<String, Object> saveOrUpdate(@RequestBody Leave leave) {
		Map<String, Object> result = new HashMap<>();
		log.info("【请假单保存或者更新】参数{}",JacksonUtil.toJSon(leave));
		
	
	     //初始化 请假单状态,请假单状态为未提交
         leave.setStatus(LeaveEmnu.LEAVE_STATUS_UNPOST.getCode());
		if (leave.getLeaveId() != null) {

			try {
		         		
				leaveService.update(leave);
				result.put("message", "请假单更新成功");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try {
				leaveService.saveAndStartProcess(leave);
				result.put("message", "请假单保存成功");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return result;
	}
	
	
	@RequestMapping("/delete")
	public @ResponseBody Map<String, Object> delete(Integer leaveId) {
		Map<String, Object> result = new HashMap<>();
		
		try {
			leaveService.deleteById(leaveId);
			result.put("message", "请假单删除成功");
			result.put("code", 1);
		} catch (Exception e) {
	
			e.printStackTrace();
		}
	
		
		return result;
	}

	
	
	
	
	
	
	@RequestMapping("/detail")
	public String detail(String fromId,Model model) {
		
		try {
			Leave leave =leaveService.selectById(Integer.parseInt(fromId));
			User user = userService.selectById(leave.getUserId());
			model.addAttribute("leave",leave);
			model.addAttribute("user",user);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "/leave/leaveDetail";
	}
	
	
	
	

	@RequestMapping("/completeTask")
	public @ResponseBody Map<String, Object> completeTask(int leaveId) {
		Map<String, Object> result = new HashMap<>();
		try {
			result=leaveService.completeTask(leaveId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
