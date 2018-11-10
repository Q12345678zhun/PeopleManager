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
import org.fkit.hrm.dao.provider.DocumentDynaSqlProvider;
import org.fkit.hrm.domain.Document;

public interface DocumentDao {
    //动态查询
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="selectWhitParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="create_date",property="createDate",javaType=java.util.Date.class),
		@Result(column="user_id",property="user",
				one=@One(select="org.fkit.hrm.dao.UserDao.selectById",fetchType=FetchType.EAGER))
	})
	List<Document>selectByPage(Map<String,Object>params);
	//查询数量，动态查询
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="count")
    int count(Map<String,Object>params);
	//插入，动态查询
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="insertDocument")
	void save(Document document);
	//更新，动态查询
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="updateDocument")
	void update(Document document);

	@Select("select * from document_inf where id=#{Id}")
	Document selectById(int id);
	@Delete("delete from document_inf where id=#{id}")
	void deleteById(Integer id);
}
