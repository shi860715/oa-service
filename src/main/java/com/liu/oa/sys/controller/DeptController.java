package com.liu.oa.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liu.oa.common.ReslutEmnu;
import com.liu.oa.framwork.model.TreeNode;
import com.liu.oa.framwork.utils.JacksonUtil;
import com.liu.oa.framwork.utils.TreeUtils;
import com.liu.oa.sys.exception.DeptException;
import com.liu.oa.sys.model.Dept;
import com.liu.oa.sys.service.DeptService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/sys/dept")
@Slf4j
public class DeptController {
	
	@Autowired
	DeptService deptService;
	
	@RequestMapping("/getTree")
	@ResponseBody
	public List<TreeNode> deptTreeNode(){
		List<Dept> depts =new ArrayList<>();
		 List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		 List<TreeNode> trees = new ArrayList<TreeNode>();
		 
		 
		try {
			
			
			depts= deptService.findAll();
			 for(Dept d:depts){
				 TreeNode node = new TreeNode();
				 node.setId(d.getDeptId());
				 node.setParentId(d.getParentId());
				 node.setText(d.getName());
				 treeNodes.add(node);
			 }
			 
			trees= TreeUtils.buildByRecursive(treeNodes);
			
		} catch (Exception e) {
			
        throw new DeptException(ReslutEmnu.DEPT_TREE_FAIL);

		}
		return trees;
	}
	
	@GetMapping("/todepts")
	public String todepts() {
		
		return "dept/deptList";
	}
	
	@RequestMapping("/depts")
	@ResponseBody
	public Map<String, Object> getDepts(){
		
		Map<String, Object> result= new HashMap<>();
		
		List<Dept> depts = new ArrayList<>();
		
		try {
			depts = deptService.findAll();
			result.put("total", depts.size());
			result.put("rows", depts);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return result;
		
	}
	
	
	
	
	
	@RequestMapping("/saveORupdate")
	@ResponseBody
	public Map<String,Object> saveORupdate(@RequestBody Dept dept){
		   Map<String, Object> resultMap = new HashMap<String, Object>();
		   
		    log.info("修改或者换删除部门{}",JacksonUtil.printJson(dept));
			if (dept.getDeptId() != 0) {
				try {
					
				deptService.update(dept);
				resultMap.put("code", 1);
				resultMap.put("message", "部门更新成功");
				
				} catch (Exception e) {
					resultMap.put("code", 0);
					resultMap.put("message", "部门更新失败");
				}
			
			} else {
				dept.setDeptId(null);
				try {
				    deptService.save(dept);
					resultMap.put("code", 1);
					resultMap.put("message", "部门添加成功");
				} catch (Exception e) {
					resultMap.put("code", 0);
					resultMap.put("message", "部门添加失败");
				}
			}
		return resultMap;
		
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> deletedept(Integer deptId){
		  Map<String, Object> resultMap = new HashMap<String, Object>();
		  
		  try {
			deptService.deleteById(deptId);
			resultMap.put("code", 1);
			resultMap.put("message", "部门删除成功");
		} catch (Exception e) {
			resultMap.put("code", 0);
			resultMap.put("message", "部门删除失败");
		}
		
		return resultMap;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
