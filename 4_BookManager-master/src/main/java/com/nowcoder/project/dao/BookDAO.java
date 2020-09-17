package com.nowcoder.project.dao;

import com.nowcoder.project.model.Book;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by nowcoder on 2018/08/04 下午3:41
 */
@Mapper
public interface BookDAO {

  String table_name = " book ";
  String insert_field = " name, author, price ";
  String select_field = " id, status, " + insert_field;
  //将程序中不变的部分和需要变化的部分分开是设计模式中的一项基本原则，
  // 这里很显然数据库表名、插入和选择范围都是基本不会变化的部分

  //下面都是Mybatis的注解与方法
  @Select({"select", select_field, "from", table_name})
  List<Book> selectAll();

  @Insert({"insert into", table_name, "(", insert_field,
      ") values (#{name},#{author},#{price})"})
    //@Insert({...})，...中是insert语句,INSERT INTO mytable(col1, col2)  VALUES(val1, val2)与"insert into", table_name, "(", insert_field,
    //      ") values (#{name},#{author},#{price})"完美对应，文字用""，变量用，隔开，#{...}取值
  int addBook(Book book);

  @Update({"update ", table_name, " set status=#{status} where id=#{id}"})
  //通过更新状态码，然后在Books.html通过状态码判断，属于delete的状态码就显示借出
  void updateBookStatus(@Param("id") int id, @Param("status") int status);
}

