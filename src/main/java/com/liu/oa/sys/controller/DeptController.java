package com.liu.oa.sys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liu.oa.common.ReslutEmnu;
import com.liu.oa.framwork.model.TreeNode;
import com.liu.oa.framwork.utils.TreeUtils;
import com.liu.oa.sys.exception.DeptException;
import com.liu.oa.sys.model.Dept;
import com.liu.oa.sys.service.DeptService;

@Controller
@RequestMapping("/sys/dept")
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
	
	@GetMapping("/depts")
	public String depts() {
		
		return "dept/dept_manager";
	}
	
	

}
