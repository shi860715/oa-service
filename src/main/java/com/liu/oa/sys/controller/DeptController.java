package com.liu.oa.sys.controller;

import java.util.List;

import javax.websocket.DeploymentException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liu.oa.common.ReslutEmnu;
import com.liu.oa.sys.exception.DeptException;
import com.liu.oa.sys.model.Dept;
import com.liu.oa.sys.service.DeptService;

@RestController
public class DeptController {
	
	@Autowired
	DeptService deptService;
	
	@GetMapping("/getDeptTree")
	public List<Dept> deptTreeNode(){
		List<Dept> depts =null;
		try {
			depts= deptService.findAll();
		} catch (Exception e) {
			
        throw new DeptException(ReslutEmnu.DEPT_TREE_FAIL);

		}
		return depts;
	}

}
