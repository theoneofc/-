package cn.edu.bm.controllers;


import cn.edu.bm.biz.LoginBiz;
import cn.edu.bm.model.User;
import cn.edu.bm.service.UserService;
import cn.edu.bm.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

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
      //为什么返回ticket实体应该是以t票为验证的程序设计逻辑
      CookieUtils.writeCookie("t", t, response);//这里实现什么1？写进cookie中。 response里面内容又是什么？response这时候空的，进去writeCookie方法里面写内容
      //同时也是因为cookie是基于response载体来实现的
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
      @RequestParam("email") String email, //从参数区去从前端过来的参数，下面用
      @RequestParam("password") String password
  ) {
    try {
      String t = loginBiz.login(email, password);
      CookieUtils.writeCookie("t", t, response);
      return "redirect:/index";
    } catch (Exception e) {
      model.addAttribute("error", e.getMessage());//进入e.getMessage()方法，内部实现的是return detailMessage;detailMessage是String类型的，所以应该是返回错误的细节把，后面404.html就进行取值展示
      return "404";
    }
  }

  @RequestMapping(path = {"/users/logout/do"}, method = {RequestMethod.GET})
  public String doLogout(
      @CookieValue("t") String t //直接取CookieValue的注释，可以可以，用法跟RequestParam和PathVariable是一样的
  ) {

    loginBiz.logout(t);
    return "redirect:/index";

  }
}
