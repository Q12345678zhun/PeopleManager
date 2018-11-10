package org.fkit.hrm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.fkit.hrm.domain.Notice;

public class NoticeDynaSqlProvider {
	public String selectWhitParam(Map<String,Object>params) {
		String sql=new SQL() {
			{
				SELECT("*");
				FROM("notice_inf");
				if(params.get("notice")!=null) {
					Notice notice=(Notice) params.get("notice");
					if(notice.getTitle()!=null&&!notice.getTitle().equals("")) {
						WHERE("title like concat('%',#{notice.title},'%'");
					}
					if(notice.getContent()!=null&&!notice.getContent().equals("")) {
						WHERE("content like concat('%',#{notice.content},'%')");
					}
				}
				
			}
		}.toString();
		if(params.get("pageModel")!=null) {
			sql +=" limit #{pageModel.firstLimitParam},#{pageModel.pageSize} ";
		}
		return sql;
	}
	
	public String count(Map<String,Object>params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM("notice_inf");
				if(params.get("notice")!=null) {
				   Notice notice=(Notice) params.get("notice");
				      if(notice.getTitle()!=null && !notice.getTitle().equals("")) {
					   WHERE ("title like concat ('%',#{notice.title},'%')");
				     }
				      if(notice.getContent()!=null && !notice.getContent().equals("")) {
				    	  WHERE("content like concat('%',#{notice.content},'%')");
				      }
				
			}
		}
		}.toString();
	}
	public String insertNotice(Notice notice) {
		return new SQL() {
			{
				INSERT_INTO("notice_inf");
				if(notice.getTitle()!=null&&!notice.getTitle().equals("")) {
					VALUES("title","#{title}");
				}
				if(notice.getContent()!=null&&!notice.getContent().equals("")) {
					VALUES("content","#{content}");
				}
				if(notice.getUser()!=null&&notice.getUser().getId()!=null) {
					VALUES("user_id","#{user.id}");
					
				}
			}
		}.toString();
	}
	
	public String updateNotice(Notice notice) {
		return new SQL() {
			{
				UPDATE("notice_inf");
				if(notice.getTitle()!=null&&notice.getTitle().equals("")) {
					SET("title = #{title}");
				}
				if(notice.getContent()!=null&&notice.getContent().equals("")) {
					SET("content = #{content}");
				}
				if(notice.getUser()!=null&&notice.getUser().getId()!=null) {
					SET("user_id = #{user.id}");
				}
				WHERE("id=#{id}");
			}
		}.toString();
	}

}
