package cn.edu.bm.model.enums;

/**
 * Created by nowcoder on 2018/08/04 下午9:38
 */

//这里展示了enum枚举类的新用法，我被惊艳到了
public enum BookStatusEnum {

  NORMAL(0),  //正常
  DELETE(1),  //删除
  RECOMMENDED(2); //推荐
  //纠错，这里的分号应该在上层的，就是最后一个元素后
  //普通是SUN, MON, TUE, WED, THU, FRI, SAT;这里还能带值，而且这个值就是单个枚举类的初始值好像

  private int value;

  BookStatusEnum(int value){
    this.value = value;
  }//相当于每个枚举类的构造函数

  public int getValue(){
    return value;
  }

}
