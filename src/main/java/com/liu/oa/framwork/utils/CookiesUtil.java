package com.liu.oa.framwork.utils;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * cookies 工具
 * 读取客户端传来的cookie
 * 向客户端写cookie
 */
public class CookiesUtil {

    public static  void  setCookie(String name,
                             String value,
                             Integer expire,
                             HttpServletResponse response){
        Cookie cookie= new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(expire);
        response.addCookie(cookie);

    }

    public static  Cookie getCookie(HttpServletRequest request,String name){
        Map<String,Cookie> cookieMap =   readCookieMap(request);
        if(cookieMap.containsKey(name)){
            return cookieMap.get(name);
        }else {
            return  null;
        }

    }

    private static Map<String,Cookie> readCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies =request.getCookies();
        if(cookies!=null){
            for(Cookie cookie :cookies){
                cookieMap.put(cookie.getName(),cookie);
            }
        }
       return  cookieMap;
    }


}
