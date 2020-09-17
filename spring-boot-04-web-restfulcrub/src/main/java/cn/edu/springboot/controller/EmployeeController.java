package cn.edu.springboot.controller;

import cn.edu.springboot.dao.DepartmentDao;
import cn.edu.springboot.dao.EmployeeDao;
import cn.edu.springboot.entities.Department;
import cn.edu.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import java.util.Collection;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;
    /*
    总结：/emps 请求主要是点击侧边栏发起的或者重定向的时候发起的，到controller来用于请求展示列表
        /emp 请求主要是单击员工编辑按钮发起的，到controller来用于请求单个员工的数据编辑任务，这是CRUD的重点请求命令
     */

    //1 获取员工信息并展示列表 list.html
    @GetMapping("/emps")
    public String list(Model model){
//        Collection<Employee> employees = employeeDao.getAll();//展示 全部列表 employeeDao
        List<Employee> employees = employeeDao.getAll();//展示 全部列表 employeeDao

        model.addAttribute("emps",employees);
        //model 没讲,跟map差不多

        //thymeleaf会自己拼串
        return "emp/list";

    }
    //用于下拉列表
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //准备部门下拉框数据
        Collection<Department> departments = departmentDao.getDepartments(); //获取所有部门名进行下拉选择 departmentDao
        model.addAttribute("depts",departments);
        return "emp/add";

    }

    //保存并重定向/emps请求，最后回到emp/list ，即列表页面
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println("保存信息：" + employee);
        //模拟添加到数据库
        employeeDao.save(employee); //添加employee
        //添加成功重定向到列表页面
        return "redirect:/emps";
    }

    //Controller转发到编辑页面，回显员工信息
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeDao.get(id);//由id来回显
        //给add.html 传递了emp，让它知道改谁
        model.addAttribute("emp",employee);

        //查页面
        //准备部门下拉框数据
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);

        //二合一页面
        return "emp/add";
    }
    //需要提交员工id
    @PutMapping("/emp")
    public String updataEmployee(Employee employee){
        System.out.println("修改数据:"+employee);
        employeeDao.save(employee);//与上上个差不多
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);//由id来删除
        return "redirect:/emps";
    }
}











//    @GetMapping("/emp")
//    public String toAddPage(){
//        Collection<Department> departments = departmentDao.getDepartments();
////        model.addAttribute("departments",departments);
//        return "emp/add";
//
//    }

