package cn.edu.springboot.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录检查2
 */

public class LoginHandlerInterceptor implements HandlerInterceptor {
    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");//获得登录检查1中的session？
                                                    // 对，获取session的属性值 getAttribute，前头不是setAttribute("loginUser",username);组合拳
        if(user == null){
            //未登录，拦截，并转发到登录页面
            //写了拦截器一定要配置出来，所以3是MyMcvConfig里面配置
            request.setAttribute("msg","没有权限请先登录");
            //转发器，转发到登录页面;把请求和响应转发出去;这里或许看看需不需要读一下源码
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;

        }else{
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
