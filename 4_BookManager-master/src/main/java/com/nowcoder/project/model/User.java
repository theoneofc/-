package com.nowcoder.project.model;

/**
 * Created by nowcoder on 2018/08/04 下午3:41
 */
public class User {

  private int id;

  private String name;

  private String email;

  /**
   * 经过md5加密
   */
  private String password;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }//按ctrl没有地方调用过这个函数，所以这个id是作为空字段来作为参数去实现功能的吗

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
