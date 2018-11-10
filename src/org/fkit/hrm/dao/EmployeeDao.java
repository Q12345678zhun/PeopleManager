package org.fkit.hrm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import org.fkit.hrm.dao.provider.EmployeeDynaSqlProvider;
import org.fkit.hrm.domain.Employee;

public interface EmployeeDao {
	//根据参数动态查询员工总数
	@SelectProvider(type=EmployeeDynaSqlProvider.class,method="count")
	int count(Map<String,Object>params);
	//根据参数动态查询员工
	@SelectProvider(type=EmployeeDynaSqlProvider.class,method="selectWhitParam")
	  @Results({
		 @Result(id=true,column="id",property="id"),
		 @Result(column="card_id",property="cardId"),
		 @Result(column="creat_data",property="creat_data"),
		 @Result(column="dept_id",property="dept",
		 one=@One(select="org.fkit.hrm.dao.DeptDao.selectById",fetchType=FetchType.EAGER)),
		 @Result(column="job_id",property="job",
		 one=@One(select="org.fkit.hrm.dao.JobDao.selectById",fetchType=FetchType.EAGER))
	})
	List<Employee>selectByPage(Map<String,Object>params);
	
	@SelectProvider(type=EmployeeDynaSqlProvider.class,method="insertEmployee")
	void save(Employee employee);
	@Delete("delete from employee_inf where id=#{id}")
	void deleteById(int id);
	
	@Select("select * from employee_inf where id=#{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="card_id",property="cardId"),
		 @Result(column="creat_data",property="creat_data"),
		 @Result(column="dept_id",property="dept",
		 one=@One(select="org.fkit.hrm.dao.DeptDao.selectById",fetchType=FetchType.EAGER)),
		 @Result(column="job_id",property="job",
		 one=@One(select="org.fkit.hrm.dao.JobDao.selectById",fetchType=FetchType.EAGER))
		
	})
	Employee selectById(int id);
	
	@SelectProvider(type=EmployeeDynaSqlProvider.class,method="updateEmployee")
	void updatEmployee(Employee employee);

}
