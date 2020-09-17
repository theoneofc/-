package cn.edu.springboot.dao;

import cn.edu.springboot.entities.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeDao {

//	private static Integer initId = 1006;

	@Options(useGeneratedKeys = true, keyProperty = "id")//这个是用来自增主键的  useGeneratedKeys是不是自动生成的key keyProperty = "id"表示这个id是用来封装主键的
	@Insert("insert into employee(id,lastName,email,gender,departmentName,birth) values(#{id},#{lastName},#{email},#{gender},#{departmentName},#{birth})")
	public void save(Employee employee);

//	private Integer id;
//	private String lastName;
//
//	private String email;
//	//1 male, 0 female
//	private Integer gender;
//	private String departmentName;
//	private Date birth;

//	public void save(Employee employee){
//		if(employee.getId() == null){
//			employee.setId(initId++);
//		}

//		employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
//		employees.put(employee.getId(), employee);
//	}

//	public Collection<Employee> getAll(){
//		return employees.values();
//	}

	@Select("select * from employee")
	List<Employee> getAll();

	@Select("select * from employee where id=#{id}")
	public Employee get(Integer id);

//	public Employee get(Integer id){
//		return employees.get(id);
//	}

	@Delete("delete from employee where id =#{id}")
	public void delete(Integer id);

//	public void delete(Integer id){
//		employees.remove(id);
//	}
}


