package com.liu.oa.sys.service;

import java.io.InputStream;
import java.util.Map;

import org.activiti.engine.repository.Deployment;

public interface WorkFlowService {
	
	
	/**
	 * 分页查找流程，且流程名称
	 * @param page
	 * @param rows
	 * @param query
	 * @return
	 */
	public Map<String,Object> getProcessDefinitions(int page ,int rows,String query) throws Exception;
    
	/**
	 *  通过文件名，和输入流 来部署流程
	 * @param fileName 流程名称
	 * @param inputStream 输入流
	 * @return  返回部署对象
	 * @throws Exception 异常
	 */
	public Deployment addDeploy(String fileName, InputStream inputStream)throws Exception;

	/**
	 *  通过部署的ID 来删除流程定义，这里要注意，如果为强制删除，则会删除启动流程相关的信息，以及历史信息
	 *  建议不要使用强制删除
	 * @param deploymentId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> deleteDefinitionByDeployMentId(String deploymentId)throws Exception; 
	
	
	/**
	 *   通过流程部署ID 来查找 流程图的名称
	 * @param deploymentId
	 * @return
	 * @throws Exception
	 */
	public InputStream getPicByDeploymentId(String deploymentId)throws Exception;
	
	/**
	 * 
	 * @param deploymentId
	 * @return
	 * @throws Exception
	 */
    public String getPicNameByDeploynmentId(String deploymentId)throws Exception;
    
    /**
             *  获取文件
     * @param deploymentId
     * @return
     */
	public InputStream getFileByDeploymentId(String deploymentId)throws Exception;
	
	/**
	 *   通过流程部署ID 来查找 流程文件名称
	 * @param deploymentId
	 * @return
	 * @throws Exception
	 */
	public String getFileNameByDeploynmentId(String deploymentId) throws Exception;
}
