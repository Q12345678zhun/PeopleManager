package org.fkit.hrm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.fkit.hrm.dao.provider.UserDynaSqlProvider;
import org.fkit.hrm.domain.User;
public interface UserDao {
	
	//根据登录名或密码查询员工或实现登录
	@Select("select * from user_inf where loginname=#{loginname} and password=#{password}")
	User selectByIdLoginnameAndPassword(
			@Param("loginname") String loginname,
			@Param("password") String password);
	//根据id查询用户
	@Select("select * from user_inf where id=#{id}")
	User selectById(int id);
	
	//根据id删除用户
	@Delete("delete from user_inf where id=#{id}")
	void deleteById(int id);
	
	//动态修改用户
	@SelectProvider(type=UserDynaSqlProvider.class,method="updateUser")
	void update(User user);
	
	//动态查询
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectWhitParam")
	List<User> selectByPage(Map<String,Object>params);
	
	//根据参数查询用户总数
	@SelectProvider(type=UserDynaSqlProvider.class,method="count")
	int count(Map<String,Object>parmas);
	
	//动态插入用户
	@SelectProvider(type=UserDynaSqlProvider.class,method="insertUser")
	void save(User user);

}
