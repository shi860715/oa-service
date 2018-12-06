package com.liu.oa.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liu.oa.common.enums.WorkFlowEmnu;
import com.liu.oa.sys.form.ComTaskForm;
import com.liu.oa.sys.model.CostPay;
import com.liu.oa.sys.model.User;
import com.liu.oa.sys.service.CostPayService;
import com.liu.oa.sys.service.UserService;
import com.liu.oa.sys.service.WorkFlowService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/sys/costpay")
@Slf4j
@Controller
public class CostPayController {
	
	@Autowired
	private CostPayService costPayService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WorkFlowService workFlowService;
	
	@RequestMapping("/detail")
	public String detail(String fromId,Model model) {
		
		log.info("【报销处理】");
		
		
		try {
			CostPay costpay =costPayService.selectById(Integer.parseInt(fromId));
			User user = userService.selectById(costpay.getUserId());
			String businessKey ="costpay:"+costpay.getCostpayId();
			
			List<PvmTransition> outcoms=workFlowService.getPvmTransitionByBusinessKey(businessKey);
			Task task =workFlowService.getTaskByBusinessKey(businessKey);
			String taskId=task.getId();
			
			
			List<String> buttons=outcoms.stream().map(x -> {
			
				return (String)x.getProperty("name");
			}).collect(Collectors.toList());
			
			log.info("leaveINfo{}",costpay);
			
			model.addAttribute("taskId",taskId);
			model.addAttribute("costpay",costpay);
			model.addAttribute("user",user);
			model.addAttribute("buttons",buttons);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/costpay/costpayDetail";
	}
	
	
	@RequestMapping("/costpays")
	public String costpays() {
		
	return "/costpay/costpayList";
	}
	
	
	@RequestMapping("/costpayByUserId")
	public @ResponseBody Map<String,Object> costPayList(int page,int rows,String query){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			result=costPayService.findAllByUserId(page, rows, query);
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		return result;
		
	} 
	
	@RequestMapping("/saveOrUpdate")
	public @ResponseBody Map<String,Object> saveOrUpdate(@RequestBody CostPay costPay){
		Map<String,Object> result = new HashMap<String, Object>();
		
		costPay.setStatus(WorkFlowEmnu.STATUS_UNPOST.getCode());
		if(costPay.getCostpayId()!=null) {
			
			try {
				costPayService.update(costPay);
				result.put("message", "报销申请更新成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put("message", "报销申请更新失败");
			}
			
		}else {
			
			try {
				costPayService.saveAndProcess(costPay);
				result.put("message", "报销申请保存成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put("message", "报销申请保存失败");
			}
		}
		
		return result;
	}
	
	
	@RequestMapping("/delete")
	public @ResponseBody Map<String,Object> delete(Integer costpayId){
		Map<String,Object> result = new HashMap<String, Object>();
		
		try {
			costPayService.deleteById(costpayId);
			result.put("message", "报销单删除成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("message", "报销单删除失败！");
		}
		return result;
	}
	
	@RequestMapping("/completeTask")
	public @ResponseBody Map<String,Object> completeTask(@RequestBody ComTaskForm taskForm){
		Map<String,Object> result = new HashMap<String, Object>();
		
		log.info("[报销管理完成任务]taskForm {}",taskForm);
		
		try {
			costPayService.completeTask("costpay:"+taskForm.getId(), taskForm.getVariables());
			result.put("message", "报销申请提交成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("message", "报销申请提交失败");
		}
		
		return result;
	}
	
	
	
	
	

}
