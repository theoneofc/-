package cn.edu.springboot.controller;

import cn.edu.springboot.dao.DepartmentDao;
import cn.edu.springboot.entities.Department;
import cn.edu.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class DepartmentController {
//    @Autowired
//    public Department department;

    @Autowired
    public DepartmentDao departmentDao;

    @GetMapping("/dep")
    public String addDep(){
        return "adddep";
    }

    @PostMapping("/department/add/do")
    public String addDep(Department department){
        System.out.println("保存信息：" + department);
        departmentDao.save(department);
        return "redirect:/emps";
    }

}
