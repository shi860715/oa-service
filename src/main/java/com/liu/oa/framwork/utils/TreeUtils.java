package com.liu.oa.framwork.utils;

import java.util.ArrayList;
import java.util.List;

import com.liu.oa.framwork.model.TreeNode;

public class TreeUtils {
	
	
	/**
	 *   使用循环的方式建树
	 * @param treeNodes
	 * @return
	 */
	public static List<TreeNode> build(List<TreeNode> treeNodes){
		List<TreeNode> trees = new ArrayList<TreeNode>();
		for(TreeNode treeNode :treeNodes){
			if(treeNode.getParentId()==null){
				trees.add(treeNode) ;
			}
			for(TreeNode it :treeNodes){
			 	if(it.getParentId()==treeNode.getId()){
			 		if(treeNode.getAttributes()==null){
			 			treeNode.setChildren(new ArrayList<TreeNode>());
			 		}
			 		treeNode.getChildren().add(it);
			 	}
			}
		}
		return trees;
	}
	
	/**
	 * 使用递归的方式进行建树
	 * @param treeNodes
	 * @return
	 */
	public static List<TreeNode> buildByRecursive(List<TreeNode> treeNodes){
		 List<TreeNode> trees = new ArrayList<TreeNode>(); 
		 for(TreeNode treeNode :treeNodes){
			 if(treeNode.getParentId()==null){
				 trees.add(findChildren(treeNode, treeNodes));
			 }
		 }
		return trees;
	}
	
	
	/**
	 * 递归查找子节点
	 * @param treeNode
	 * @param treeNodes
	 * @return
	 */
	public static TreeNode findChildren(TreeNode treeNode,List<TreeNode> treeNodes){
		
		for(TreeNode it:treeNodes){
			
			if(treeNode.getId()==it.getParentId()){
				if (treeNode.getChildren()==null) {
					treeNode.setChildren(new ArrayList<TreeNode>());
				}
				treeNode.getChildren().add(findChildren(it, treeNodes));
			}
			
		}
		return treeNode;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
