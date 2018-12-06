package com.liu.oa.sys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liu.oa.sys.model.CostPay;
import com.liu.oa.sys.service.CostPayService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/sys/costpay")
@Slf4j
@Controller
public class CostPayController {
	
	@Autowired
	private CostPayService costPayService;
	
	@RequestMapping("/detail")
	public String detail(String fromId) {
		
		log.info("【报销处理】");
		
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
		
	
		if(costPay.getCostpayId()!=null) {
			
			try {
				costPayService.update(costPay);
				result.put("message", "出差申请更新成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put("message", "出差申请更新失败");
			}
			
		}else {
			
			try {
				costPayService.saveAndProcess(costPay);
				result.put("message", "出差申请保存成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put("message", "出差申请保存失败");
			}
		}
		
		return result;
	}
	
	

}
