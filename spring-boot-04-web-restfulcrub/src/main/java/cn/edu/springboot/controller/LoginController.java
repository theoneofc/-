package cn.edu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session){
        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
            //登录检查1
            //登录成功，把用户信息方法放session中，防止表单重复提交，重定向到后台页面 初始目的：防止表单重复提交
            session.setAttribute("loginUser",username);

            //重定向2
            return "redirect:/main.html";
//            return "dashboard";

        }else {
            map.put("msg", "用户名密码登录错误");
            return "login";
        }

    }
}
