package org.fkit.hrm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.fkit.hrm.domain.User;

public class UserDynaSqlProvider {
	//分页动态查询
	public String selectWhitParam(Map<String,Object> params) {
		String sql=new SQL(){
			{
			SELECT("*");
			FROM("user_inf");
			if(params.get("user") !=null) {
				User user=(User)params.get("user");
				if(user.getUsername()!=null&& !user.getUsername().equals("")) {
					WHERE("username like concat('%',#{user.username},'%')");
				}
				
				
			}
		
		}
	}.toString();
	if(params.get("pageModel")!=null) {
		sql +=" limit #{pageModel.firstLimitParam},#{pageModel.pageSize} ";
	}
		return sql;
		
	}
	//动态查询总数量
	public String count(Map<String,Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM("user_inf");
				if(params.get("user")!=null) {
					User user=(User) params.get("user");
					if(user.getUsername()!=null&&!user.getUsername().equals("")) {
						WHERE("username like concat('%',#{user.username},'%')");
					}
					
					
					
				}
				
			}
		}.toString();
	}
	
  //动态插入
	public String insertUser(User user) {
		return new SQL() {
			{
				INSERT_INTO("user_inf");
				if(user.getUsername()!=null && !user.getUsername().equals("")) {
					VALUES("username","#{username}");
				}
				if(user.getLoginname()!=null && !user.getLoginname().equals("")) {
					VALUES("loginname","#{loginname}");
				}
				if(user.getPassword()!=null && !user.getPassword().equals("")) {
					VALUES("password","#{password}");
				}
				if(user.getStatus()!=null&&user.getStatus()!=0) {
					VALUES("status","#{status}");
				}
			
			}
			
		}.toString();
		
		
	}
//动态更新
	public String updateUser(User user) {
		return new SQL() {
			{
				UPDATE("user_inf");
				if(user.getUsername()!=null) {
					SET("username=#{username}");
				}
				if(user.getLoginname()!=null) {
					SET("loginname=#{loginname}");
				}
				if(user.getPassword()!=null) {
					SET("password=#{password}");
				}
				if(user.getCreateDate()!=null) {
					SET("createdate=#{createDate}");
				}
				if(user.getStatus()!=null) {
					SET("status=#{status}");
				}
			}
		}.toString();
	}
}
