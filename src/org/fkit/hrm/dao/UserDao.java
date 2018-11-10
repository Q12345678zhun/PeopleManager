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
	
	//���ݵ�¼���������ѯԱ����ʵ�ֵ�¼
	@Select("select * from user_inf where loginname=#{loginname} and password=#{password}")
	User selectByIdLoginnameAndPassword(
			@Param("loginname") String loginname,
			@Param("password") String password);
	//����id��ѯ�û�
	@Select("select * from user_inf where id=#{id}")
	User selectById(int id);
	
	//����idɾ���û�
	@Delete("delete from user_inf where id=#{id}")
	void deleteById(int id);
	
	//��̬�޸��û�
	@SelectProvider(type=UserDynaSqlProvider.class,method="updateUser")
	void update(User user);
	
	//��̬��ѯ
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectWhitParam")
	List<User> selectByPage(Map<String,Object>params);
	
	//���ݲ�����ѯ�û�����
	@SelectProvider(type=UserDynaSqlProvider.class,method="count")
	int count(Map<String,Object>parmas);
	
	//��̬�����û�
	@SelectProvider(type=UserDynaSqlProvider.class,method="insertUser")
	void save(User user);

}
