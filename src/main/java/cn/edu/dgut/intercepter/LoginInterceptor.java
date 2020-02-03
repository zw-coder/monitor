package cn.edu.dgut.intercepter;

import cn.edu.dgut.domain.User;
import cn.edu.dgut.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //获取请求路径
        String url = httpServletRequest.getRequestURI();
        if(url.indexOf("/login")>=0)
        {
            return true;
        }
        HttpSession session = httpServletRequest.getSession();
        User user  = (User) session.getAttribute("User");
        //用户不为空继续执行
        if(user!=null)
        {
            return  true;
        }
        //不符合条件提示，并转发到登陆
        httpServletRequest.setAttribute("msg","你还没登陆,请先登陆");
        httpServletRequest.getRequestDispatcher("/pages/login.jsp").forward(httpServletRequest,httpServletResponse);
        return  false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
