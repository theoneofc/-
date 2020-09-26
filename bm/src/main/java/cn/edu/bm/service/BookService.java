package cn.edu.bm.service;


import cn.edu.bm.dao.BookDAO;
import cn.edu.bm.model.Book;
import cn.edu.bm.model.enums.BookStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nowcoder on 2018/08/04 下午3:41
 */
@Service
public class BookService {

  @Autowired
  private BookDAO bookDAO;

  public List<Book> getAllBooks() {
    return bookDAO.selectAll();
  }

  /*public List<Book> getAllBooks() {
        try {
            return bookDAO.selectAll();
        } catch (Exception e) {
            logger.error(e);
            return null;
           //- 或者抛出自定义的异常 -
}
    }*/


  public int addBooks(Book book) {
    return bookDAO.addBook(book);
  }

  public void deleteBooks(int id) { bookDAO.updateBookStatus(id, BookStatusEnum.DELETE.getValue()); }
  //BookStatusEnum.DELETE.getValue() BookStatusEnum.DELETE会得到BookStatusEnum类型的DELETE枚举对象，然后再引用getValue()获取状态值
  //然后bookDAO.updateBookStatus(id, status)就进行更新了

  public void recoverBooks(int id) {
    bookDAO.updateBookStatus(id, BookStatusEnum.NORMAL.getValue());
  }

  public List<Book> searchForBooks(String authorOrName) { //getBooks 这个名好似好些
    return bookDAO.selectBooks(authorOrName);//因为name和author都是String类型无法重载，所以这里就做了一定的修改
  }
 /* public List<Book> searchForBooks(String name) { //getBooks 这个名好似好些
    return bookDAO.selectBooksByAuthor(name);
  }*/
}
