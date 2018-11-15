package com.liu.oa.framwork.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheManager;

import com.alibaba.druid.sql.visitor.functions.Concat;
import com.liu.oa.common.RequestHolder;
import com.liu.oa.common.contants.CookiesConstant;
import com.liu.oa.framwork.utils.JacksonUtil;
import com.liu.oa.sys.model.User;
import com.liu.oa.sys.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(filterName = "loginFilter", urlPatterns = { "/*" })
public class LoginFilter implements Filter {
	
	@Autowired
	private RedisService redisService;

	private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(
			new HashSet<>(Arrays.asList("/druid/*", "/tologin", "/logout", "/sys/login", ".js", ".css", ".ico")));

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) srequest;
		HttpServletResponse response = (HttpServletResponse) sresponse;
		
		Cookie[] cookies = request.getCookies();

		String[] urls = { "/login", "/sys/login", ".js", ".css", ".ico", ".jpg", ".png", "/tologin", "/druid/*",
				"/logout", ".gif" };
		String spath = request.getServletPath();
		boolean flag = true;
		for (String str : urls) {
			if (spath.indexOf(str) != -1) {
				flag = false;
				break;
			}
		}
		log.info("请求地址{}结果{}", spath, flag);

		if (flag) {
			    
			    if(cookies.length>1) {
			    	for(Cookie c :cookies) {
						if(c.getName().equals(CookiesConstant.COOKIES_NAME)){
							String userInfo =redisService.get(c.getValue());
							User user =JacksonUtil.readValue(userInfo, User.class);
							RequestHolder.add(user);
							RequestHolder.add(request);
							
						}
						
						
					}
			    	chain.doFilter(request, response);
			    	
			    }else {
					response.sendRedirect("/tologin");
				}
			
				
			
			

		} else {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
