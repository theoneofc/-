package com.nowcoder.project.controllers;


import com.nowcoder.project.biz.LoginBiz;
import com.nowcoder.project.model.User;
import com.nowcoder.project.service.UserService;
import com.nowcoder.project.utils.CookieUtils;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by nowcoder on 2018/08/07 下午2:14
 */
@Controller
public class LoginController {

  @Autowired
  private LoginBiz loginBiz;

  @Autowired
  private UserService userService;

  @RequestMapping(path = {"/users/register"}, method = {RequestMethod.GET})
  public String register() {
    return "login/register";
  }

  @RequestMapping(path = {"/users/register/do"}, method = {RequestMethod.POST})
  public String doRegister(
      Model model,
      HttpServletResponse response,
      @RequestParam("name") String name,
      @RequestParam("email") String email,
      @RequestParam("password") String password
  ) {

    User user = new User();//new
    user.setName(name);
    user.setEmail(email);
    user.setPassword(password);

    try {
      String t = loginBiz.register(user);//进入register方法看里面的实现得知返回的t是用户Ticket里面的ticket实体
      CookieUtils.writeCookie("t", t, response);//这里实现什么1？写进cookie中。 response里面内容又是什么？response这时候空的，进去writeCookie方法里面写内容
      return "redirect:/index";
    } catch (Exception e) { //这里的异常是loginBiz.register(user)里面的登录异常抛出的，后面有model传给404，404再取值进行显示"用户邮箱已经存在！"
      model.addAttribute("error", e.getMessage());
      return "404";
    }
  }

  @RequestMapping(path = {"/users/login"}, method = {RequestMethod.GET})
  public String login() {
    return "login/login";
  }

  @RequestMapping(path = {"/users/login/do"}, method = {RequestMethod.POST})
  public String doLogin(
      Model model,
      HttpServletResponse response,
      @RequestParam("email") String email,
      @RequestParam("password") String password
  ) {
    try {
      String t = loginBiz.login(email, password);
      CookieUtils.writeCookie("t", t, response);
      return "redirect:/index";
    } catch (Exception e) {
      model.addAttribute("error", e.getMessage());
      return "404";
    }
  }

  @RequestMapping(path = {"/users/logout/do"}, method = {RequestMethod.GET})
  public String doLogout(
      @CookieValue("t") String t
  ) {

    loginBiz.logout(t);
    return "redirect:/index";

  }
}
