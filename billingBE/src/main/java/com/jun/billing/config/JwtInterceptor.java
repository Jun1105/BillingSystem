package com.jun.billing.config;

import com.jun.billing.utils.JwtUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws RuntimeException {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        if("/login".equals(request.getServletPath())){
            return true;
        }

        //从 http 请求头中取出 token
        String token = request.getHeader("authorization");

        if (token == null) {
            throw new RuntimeException("无 token ，请重新登陆");
        }

        //验证 token
        JwtUtil.checkSign(token);

        //验证通过后， 这里测试取出JWT中存放的数据
        //获取 token 中的 userId
        String userId = JwtUtil.getUserId(token);

        //获取 token 中的其他数据
//        Map<String, Object> info = JwtUtil.getInfo(token);
//        info.forEach((k, v) -> System.out.println(k + ":" + v));
        return true;
    }
}
