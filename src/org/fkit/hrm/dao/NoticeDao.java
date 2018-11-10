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
import org.fkit.hrm.dao.provider.NoticeDynaSqlProvider;
import org.fkit.hrm.domain.Notice;

public interface NoticeDao {
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="selectWhitParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="create_date",property="createDate",javaType=java.util.Date.class),
		@Result(column="user_id",property="user",
				one=@One(select="org.fkit.hrm.dao.UserDao.selectById",fetchType=FetchType.EAGER))
	})
	List<Notice> selectByPage(Map<String,Object>params);
   
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="count")
	int count(Map<String,Object>params);
	
	@Select("select * from notice_inf where id=#{id}")
	Notice selectById(int id);
	
	@Delete("delete from notice_inf where id=#{id}")
	void deleteById(int id);
	
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="insertNotice")
	void save(Notice notice);
	
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="updateNotice")
	void update(Notice notice);
}
