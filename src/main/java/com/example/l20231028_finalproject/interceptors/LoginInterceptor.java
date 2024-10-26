package com.example.l20231028_finalproject.interceptors;

import com.example.l20231028_finalproject.pojo.UserVo;
import com.example.l20231028_finalproject.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {

            UserVo cuser = (UserVo) request.getSession().getAttribute("currentuser");
            if (cuser == null) {
                request.setAttribute("errmsg","Username or Password is wrong!");

                request.getRequestDispatcher(request.getContextPath() + "/tologin").forward(request, response);
                return false;
            }
            System.out.println("Current userasdasdasdasdasda : " + cuser.getUsername());
            ThreadLocalUtil.set(cuser);
            UserVo checkTL = ThreadLocalUtil.get();
            System.out.println("Check wallet id : " + checkTL.getWallet_id());

        }
        return true;

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空ThreadLocal中的数据
        ThreadLocalUtil.remove();
    }
}
