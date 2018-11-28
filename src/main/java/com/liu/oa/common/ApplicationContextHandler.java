package com.liu.oa.common;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHandler implements ApplicationContextAware {
	
	private static ApplicationContext context = null;
	 

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.context = applicationContext;
    }
 
    // 传入线程中
    public static <T> T getBean(String beanName) {
        return (T) context.getBean(beanName);
    }
 
    // 国际化使用
    public static String getMessage(String key) {
        return context.getMessage(key, null, Locale.getDefault());
    }
 
    // 获取当前环境
    public static String[] getActiveProfiles() {
        return context.getEnvironment().getActiveProfiles();
    }
 
    // 判断当前环境是否为test/local
    public static boolean isTestEnv(){
        String[] activeProfiles = getActiveProfiles();
        if (activeProfiles.length<1){
            return false;
        }
 
        for (String activeProfile : activeProfiles) {
            if (StringUtils.equals(activeProfile,"local")||
                    StringUtils.equals(activeProfile,"dev")){
                return true;
            }
        }
        return false;
    }

	

}
