package com.liu.oa.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liu.oa.common.ReslutEmnu;
import com.liu.oa.framwork.model.TreeNode;
import com.liu.oa.framwork.utils.JacksonUtil;
import com.liu.oa.framwork.utils.TreeUtils;
import com.liu.oa.sys.exception.DeptException;
import com.liu.oa.sys.model.Dept;
import com.liu.oa.sys.model.Menu;
import com.liu.oa.sys.service.MenuService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/sys/menu")
@Slf4j
public class MenuController {
	
	@Autowired
	MenuService menuService;
	
	
	@RequestMapping("/tomenus")
	public String toMenu() {
		
		return "menu/menuList";
		
	}
	
	
	
	@RequestMapping("/getMenuTree")
	@ResponseBody
	public List<TreeNode> getMenuTree(){
		 List<Menu> menus =new ArrayList<>();
		 List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		 List<TreeNode> trees = new ArrayList<TreeNode>();
		 
		 
		try {
			
			menus= menuService.findAll();
			 for(Menu d:menus){
				 TreeNode node = new TreeNode();
				 node.setId(d.getMenuId());
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
	
	
	@RequestMapping("/menus")
	@ResponseBody
   public Map<String, Object> menus(){
	   
		Map<String, Object> result= new HashMap<>();
	   
		List<Menu> menus = new ArrayList<>();
		try {
			menus=menuService.findAll();
			log.info("菜单列表{}",JacksonUtil.printJson(menus));
			result.put("total", menus.size());
			result.put("rows", menus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	return result;
   }
	
	
	
	
	@RequestMapping("/saveORupdate")
	@ResponseBody
	public Map<String,Object> saveORupdate(@RequestBody Menu menu){
		   Map<String, Object> resultMap = new HashMap<String, Object>();
		   
		    log.info("修改或者换删除资源{}",JacksonUtil.printJson(menu));
			if (menu.getMenuId() != 0) {
				try {
					
					menuService.update(menu);
				resultMap.put("code", 1);
				resultMap.put("message", "资源更新成功");
				
				} catch (Exception e) {
					resultMap.put("code", 0);
					resultMap.put("message", "资源更新失败");
				}
			
			} else {
				menu.setMenuId(null);
				try {
					menuService.save(menu);
					resultMap.put("code", 1);
					resultMap.put("message", "资源添加成功");
				} catch (Exception e) {
					resultMap.put("code", 0);
					resultMap.put("message", "资源添加失败");
				}
			}
		return resultMap;
		
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> deletedept(Integer menuId){
		  Map<String, Object> resultMap = new HashMap<String, Object>();
		  
		  try {
			  menuService.deleteById(menuId);
			resultMap.put("code", 1);
			resultMap.put("message", "资源删除成功");
		} catch (Exception e) {
			resultMap.put("code", 0);
			resultMap.put("message", "资源删除失败");
		}
		
		return resultMap;
		
	}

}
