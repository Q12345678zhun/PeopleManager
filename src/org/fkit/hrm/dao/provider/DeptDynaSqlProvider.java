package org.fkit.hrm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.fkit.hrm.domain.Dept;

public class DeptDynaSqlProvider {
	//��ҳ��̬��ѯ
	public String selectWhitParam(Map<String,Object> params) {
		String sql=new SQL() {
			{
				SELECT("*");
				FROM("dept_inf");
				if(params.get("dept")!=null) {
					Dept dept=(Dept) params.get("dept");
					if(dept.getName()!=null&&!dept.getName().equals("")) {
						WHERE("name like concat ('%',#{dept.name},'%')");
					}
				}
				
			}
		}.toString();
		if(params.get("pageModel")!=null) {
			sql +=" limit #{pageModel.firstLimitParam},#{pageModel.pageSize} ";
		}
		return sql;
	}
	//��̬��ѯ������
	public String count(Map<String,Object>params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM("dept_inf");
				if(params.get("dept")!=null) {
					Dept dept=(Dept) params.get("dept");
					if(dept.getName()!=null&&!dept.getName().equals("")) {
						WHERE ("name like concat ('%',#{dept.name},'%'");
					}
				}
			}
		}.toString();
	}
	//��̬����
	public String insertDept(Dept dept) {
		return new SQL() {
			{
				INSERT_INTO("dept_inf");
				if(dept.getName()!=null&& !dept.getName().equals("")) {
					VALUES("name","#{name}");
				}
				if(dept.getRemark()!=null &&!dept.getRemark().equals("")) {
					VALUES("remark","#{remark}");
				}
				
			}
		}.toString();
	}
	//��̬����
	public String updateDept(Dept dept) {
		return new SQL(){
			{
				UPDATE("dept_inf");
				if(dept.getName()!=null) {
					SET("name=#{name}");
				}
				if(dept.getRemark()!=null) {
					SET("remark=#{remark}");
				}
				WHERE ("id=#{id}");
				
			}
		}.toString();
	}


}
