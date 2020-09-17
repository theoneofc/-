package com.nowcoder.project.controllers;

import com.nowcoder.project.model.Book;
import com.nowcoder.project.model.User;
import com.nowcoder.project.service.BookService;
import com.nowcoder.project.service.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by nowcoder on 2018/08/04 下午3:41
 */
@Controller
public class BookController {

  @Autowired
  private BookService bookService;

  @Autowired
  private HostHolder hostHolder;
  @RequestMapping(path = {"/index"}, method = {RequestMethod.GET})
  //等价于@GetMapping("/index"),一般情况下都是后加（），注解也是，然后这里的path和method就很有特点了，都是{}，觉得跟赋值有关系

  public String bookList(Model model) {

    User host = hostHolder.getUser();//得到带有t票的叫host的User对象，后面传给books.html，去进行一个登陆展示判断，也就是host判断
    if (host != null) {
      model.addAttribute("host", host);
    }
    loadAllBooksView(model);//这里是提取所有的书，可以一直Ctrl进入查看从头到尾的实现，底层是Dao对数据库进行整个表的查询
    return "book/books";

  }

  @RequestMapping(path = {"/books/add"}, method = {RequestMethod.GET})
  public String addBook() {
    return "book/addbook";
  }


  @RequestMapping(path = {"/books/add/do"}, method = {RequestMethod.POST})
  public String doAddBook(
      @RequestParam("name") String name,
      @RequestParam("author") String author,
      @RequestParam("price") String price
  ) {

    Book book = new Book();
    book.setName(name);
    book.setAuthor(author);
    book.setPrice(price);
    bookService.addBooks(book);

    return "redirect:/index";

  }

  @RequestMapping(path = {"/books/{bookId:[0-9]+}/delete"}, method = {RequestMethod.GET})//{bookId:[0-9]+}正则写法，表示id是0-9中取一个或n个，也就是可以相当于整数级
  public String deleteBook(
      @PathVariable("bookId") int bookId
  ) {

    bookService.deleteBooks(bookId);
    return "redirect:/index";

  }

  @RequestMapping(path = {"/books/{bookId:[0-9]+}/recover"}, method = {RequestMethod.GET})
  public String recoverBook(
      @PathVariable("bookId") int bookId
  ) {

    bookService.recoverBooks(bookId);
    return "redirect:/index";

  }

  /**
   * 为model加载所有的book
   */
  private void loadAllBooksView(Model model) {
    model.addAttribute("books", bookService.getAllBooks());
  }

}
