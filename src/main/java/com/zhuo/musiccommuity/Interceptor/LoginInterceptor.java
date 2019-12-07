package com.zhuo.musiccommuity.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Cookie []cookie = request.getCookies();
        if(cookie!=null){
            for (Cookie c:cookie
                    ) {
                if(c.getName().equals("ssocookie")){
                    if(c.getValue().equals("user")||c.getValue().equals("musician")){
                        return true;
                    }
                }
            }
        }
            try {
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                OutputStream os = response.getOutputStream();
                os.write("你还没登陆，请返回主页登陆".getBytes("utf-8"));
                os.write("<a href='/index'>返回主页</a>".getBytes("utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        return false;
    }
}
