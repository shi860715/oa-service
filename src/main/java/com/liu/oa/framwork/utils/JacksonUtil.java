package com.liu.oa.framwork.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
* @Title: JacksonUtil.java 
* @Package com.liu.oa.framewrok.util 
* @Description: jackson 转换工具 
* @author MR.T shi860715@126.com 
* @date 2017年12月11日 上午11:34:00 
* @version V1.0
 */
public class JacksonUtil {
	
	public static ObjectMapper objectMapper;
	
	
	
	public static <T> T readValue(String jsonStr,Class<T> valueType){
		
		if (objectMapper ==null ) {  
            objectMapper = new ObjectMapper();  
        } 
		
		try {  
            return objectMapper.readValue(jsonStr, valueType);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
		
		return null;
	}
	
	
	/** 
     * json数组转List 
     * @param jsonStr 
     * @param valueTypeRef 
     * @return 
     */  
    public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef){  
        if (objectMapper == null) {  
            objectMapper = new ObjectMapper();  
        }  
  
        try {  
            return objectMapper.readValue(jsonStr, valueTypeRef);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return null;  
    }  
    
    
    /** 
     * 把JavaBean转换为json字符串 
     *  
     * @param object 
     * @return 
     */  
    public static String toJSon(Object object) {  
        if (objectMapper == null) {  
            objectMapper = new ObjectMapper();  
        }  
  
        try {  
            return objectMapper.writeValueAsString(object);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return null ;  
    }

    public static String printJson(Object object){
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null ;


    }

	
	
	

}
