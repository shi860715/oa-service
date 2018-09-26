package com.liu.oa.framwork.config;



import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;


@Configuration
//@ServletComponentScan //用于扫描所有的servlet filter  listener
public class DruidConfig {

	@ConfigurationProperties(prefix="spring.datasource")
	@Bean
	public DataSource druid() {
		
		return new DruidDataSource();
	}
	
//	配置管理后台的servlet
	@Bean
	public ServletRegistrationBean statViewServlet() {
		
		
		ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
		Map<String, String> initParameters = new HashMap<>();
//		 配置参数 用户名和密码，访问地址
		initParameters.put("loginUsername", "root");
		initParameters.put("loginPassword", "123");
		initParameters.put("allow", "127.0.0.1");
		
		bean.setInitParameters(initParameters);
		return bean;
		
	}
	
//	配置监控filter
	@Bean
	public FilterRegistrationBean webstatFilter() {
		
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new WebStatFilter());
		Map<String, String> initParameters = new HashMap<>();
		initParameters.put("exclusions", "*.js,*.jpg,*.png,*.gif,*.ico,*.css,/druid/*");
		bean.setInitParameters(initParameters);
		bean.addUrlPatterns("/*");
		return bean;
		
	}
	
	
	
	
	
	
	
	
	
	
}
