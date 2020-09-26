package cn.edu.bm.controllers;


import cn.edu.bm.model.Book;
import cn.edu.bm.model.User;
import cn.edu.bm.service.BookService;
import cn.edu.bm.service.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
  //等价于@GetMapping("/index"),一般情况下都是后加（），注解也是，然后这里的path和method就很有特点了，都是{}，觉得跟赋值有关系--精进进行了一定研究，可查阅

  public String bookList(Model model) {

    User host = hostHolder.getUser();//得到带有t票的叫host的User对象，后面传给books.html，去进行一个登陆展示判断，也就是host判断
    if (host != null) {
      model.addAttribute("host", host); //同样通过model来传递过去
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
      @RequestParam("name") String name, //从请求参数区获取参数，内部是基于HttpServletRequest对象request实现的
      @RequestParam("author") String author,
      @RequestParam("price") String price
  ) {

    Book book = new Book();//这里是new来创建对象，确实IOC好像是对大多数而已，不是全部，另外自己new的方式是等价，一句话能用IOC就用，不能就自己new也可
    book.setName(name);
    book.setAuthor(author);
    book.setPrice(price);
    bookService.addBooks(book);//调用三个set方法之后再用bookService来实现数据库的写入

    return "redirect:/index";

  }

  @RequestMapping(path = {"/books/{bookId:[0-9]+}/delete"}, method = {RequestMethod.GET})//{bookId:[0-9]+}正则写法，表示id是0-9中取一个或n个，也就是可以相当于整数级
  public String deleteBook(
      @PathVariable("bookId") int bookId //从url上获取参数
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
  private void loadAllBooksView(Model model) { //这里用model来把一些视图传递过去，model.addAttribute()用法类似map(String, Object)
    model.addAttribute("books", bookService.getAllBooks());
  }

}
