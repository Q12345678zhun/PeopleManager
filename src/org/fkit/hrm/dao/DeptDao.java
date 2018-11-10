package org.fkit.hrm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.fkit.hrm.dao.provider.DeptDynaSqlProvider;
import org.fkit.hrm.domain.Dept;

public interface DeptDao {
	@SelectProvider(type=DeptDynaSqlProvider.class,method="selectWhitParam")
	List<Dept> selectByPage(Map<String,Object>params);
	
	@SelectProvider(type=DeptDynaSqlProvider.class,method="count")
	int count(Map<String,Object>params);
	//查询所有的部门
	@Select("select * from dept_inf")
	List<Dept> selectAllDept();
	//根据id查询部门
	@Select("select * from dept_inf where id=#{id}")
	Dept selectById(int id);
	//根据id删除部门
	@Delete("delete from dept_inf where id=#{id}")
	void deleteByID(int id);
	@SelectProvider(type=DeptDynaSqlProvider.class,method="insertDept")
	void save(Dept dept);
	@SelectProvider(type=DeptDynaSqlProvider.class,method="updateDept")
	void update(Dept dept);
	

}
