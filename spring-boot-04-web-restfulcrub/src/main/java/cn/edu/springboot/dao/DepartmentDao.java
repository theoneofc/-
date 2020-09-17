package cn.edu.springboot.dao;

import cn.edu.springboot.entities.Department;
import cn.edu.springboot.entities.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.Collection;
import java.util.List;

//@Repository
@Mapper

public interface DepartmentDao {
	
//	public Collection<Department> getDepartments(){
//		return departments.values();
//	}

	@Select("select * from department")
	public List<Department> getDepartments();

	@Options(useGeneratedKeys = true, keyProperty = "id")//这个是用来自增主键的  useGeneratedKeys是不是自动生成的key keyProperty = "id"表示这个id是用来封装主键的
	@Insert("insert into department(id,departmentName) values(#{id},#{departmentName})")
	public void save(Department department);


//没用到过
//	public Department getDepartment(Integer id){
//		return departments.get(id);
//	}
	
}


