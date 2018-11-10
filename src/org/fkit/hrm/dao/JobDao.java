package org.fkit.hrm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.fkit.hrm.dao.provider.JobDynaSqlProvider;
import org.fkit.hrm.domain.Job;

public interface JobDao {
	@Select("select * from job_inf where id=#{id}")
	Job selectById(int id);
	@Select("select * from job_inf")
	List<Job>selectAllJob();
	@SelectProvider(type=JobDynaSqlProvider.class,method="selectWhitParam")
	List<Job>selectByPage(Map<String,Object>params);
	@SelectProvider(type=JobDynaSqlProvider.class,method="count")
	int count(Map<String,Object>param);
	@Delete("delete from job_inf where id=#{id}")
	void DeleteById(int id);
	//动态插入部门
	@SelectProvider(type=JobDynaSqlProvider.class,method="insertJob")
	void save(Job job);
	//动态修改用户
	@SelectProvider(type=JobDynaSqlProvider.class,method="updateJob")
	void update(Job job);

}
