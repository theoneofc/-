package cn.edu.bm.dao;

import cn.edu.bm.model.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nowcoder on 2018/08/04 下午3:41
 */
@Repository

@Mapper
public interface BookDAO {  /*Dao类竟然都是interface，怪不得我看着写着很奇怪，那么如果是抽象的interface，那么怎么实现实际功能的？疑惑*/

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


  //下面增加查书的操作，一般提供书名和作者查书。还需要补充前端的查书接口展示结果功能
  @Select({"select", select_field, "from", table_name, "where name=#{authorOrName} or author=#{authorOrName}"})
  List<Book> selectBooks(@Param("authorOrName") String authorOrName);

  /*@Select({"select", select_field, "from", table_name, "where author=#{author}"})
  List<Book> selectBooksByAuthor(@Param("author") String author);*/
}