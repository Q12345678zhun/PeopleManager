package org.fkit.hrm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.fkit.hrm.domain.Employee;

public class EmployeeDynaSqlProvider {
	public String selectWhitParam(Map<String,Object>params) {
		String sql=new SQL() {
			{
				SELECT("*");
				FROM("employee_inf");
				if(params.get("employee")!=null){
					Employee employee=(Employee) params.get("employee");
					if(employee.getDept()!=null && employee.getDept().getId()!=null&&employee.getDept().getId()!=0) {
						WHERE("dept_id=#{employee.dept.id}");
					}
					if(employee.getJob()!=null && employee.getJob().getId()!=null&&employee.getJob().getId()!=0) {
						WHERE("job_id=#{employee.job.id}"); 
					}
					if(employee.getName()!=null&&!employee.getName().equals("")) {
						WHERE("name like concat ('%',#{employee.name},'%')");
					}
					if(employee.getCardId()!=null&&!employee.getCardId().equals("")) {
						WHERE("card_id like concat ('%',#{employee.cardId},'%')");
					}
					if( employee.getSex()!=null) {
						WHERE("sex=#{employee.sex}");
					}
					
				}
			}
		}.toString();
		
		if(params.get("pageModel")!=null) {
			sql +=" limit #{pageModel.firstLimitParam},#{pageModel.pageSize} ";
		}
		return sql;
		
	}
	public String count(Map<String,Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM("employee_inf");
				if(params.get("employee")!=null) {
					Employee employee=(Employee) params.get("employee");
					if(employee.getDept()!=null&&employee.getDept().getId()!=null&&employee.getDept().getId()!=0) {
						WHERE("dept_id=#{employee.dept.id}");
					}
					if(employee.getJob()!=null&&employee.getJob().getId()!=null&&employee.getJob().getId()!=0) {
						WHERE("job_id=#{employee.job.id}");
					}
					if(employee.getName()!=null && !employee.getName().equals("")) {
						WHERE("name like concat('%',#{employee.name},'%')");
					}
					if(employee.getCardId()!=null&&!employee.getCardId().equals("")) {
						WHERE("card_id like concat('%','#{employee.cardId},'%')");
					}
					if(employee.getSex()!=null&&employee.getSex()!=0) {
						WHERE ("sex=#{employee.sex}");
						
					}
				}
				
			}
		}.toString();
	}
	public String insertEmployee(Employee employee) {
		return new SQL() {
			{
				INSERT_INTO("employee_inf");
				if(employee.getName()!=null) {
					VALUES("name","#{name}");
				}
				if(employee.getCardId()!=null) {
					VALUES("card_id","#{cardId}");
				}
				if(employee.getSex()!=0) {
					VALUES("sex","#{sex}");
				}
				if(employee.getHobby()!=null) {
					VALUES("hobby","#{hobby}");
				}
				if(employee.getDept()!=null) {
					VALUES("dept_id","#{dept.id}");
				}
				if(employee.getJob()!=null) {
					VALUES("job_id","#{job.id}");
				}
				if(employee.getTel()!=null) {
					VALUES("tel","#{tel}");
				}
				if(employee.getAddress()!=null) {
					VALUES("address","#{address}");
				}
				
			}
		}.toString();
	}
	public String updateEmployee(Employee employee) {
		return new SQL() {
			{
				UPDATE("employee_inf");
				if(employee.getName()!=null) {
					SET("name=#{name}");
				}
				if(employee.getCardId()!=null) {
					SET("card_id=#{cardId}");
				}
				
				if(employee.getAddress()!=null) {
					SET("address=#{address}");
				}
				if(employee.getTel()!=null) {
					SET("tel=#{tel}");
				}
				if(employee.getSex()!=0) {
					SET("sex=#{sex}");
				}
				if(employee.getDept()!=null) {
					SET("dept_id=#{dept.id}");
				}
				if(employee.getJob()!=null) {
					SET("job_id=#{job.id}");
				}
				if(employee.getCreat_data()!=null) {
					SET("creat_data=#{creat_data}");
				}
				if(employee.getHobby()!=null) {
					SET("hobby=#{hobby}");
				}
				WHERE ("id=#{id}");
				
			}
		}.toString();
		
	}
	

}
