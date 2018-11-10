package org.fkit.hrm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.fkit.hrm.domain.Document;

public class DocumentDynaSqlProvider {
	public String selectWhitParam(Map<String,Object>params) {
		String sql=new SQL() {
			{
				SELECT("*");
				FROM("document_inf");
				if(params.get("document")!=null) {
					Document document=(Document) params.get("document");
					if(document.getTitle()!=null&&!document.getTitle().equals("")) {
						WHERE("title like concat('%',#{document.title},'%')");
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
				FROM("document_inf");
				if(params.get("document")!=null) {
					Document document=(Document) params.get("document");
					if(document.getTitle()!=null&&!document.getTitle().equals("")) {
						WHERE("titel like concat('%',#{document.title},'%')");
					}
				}
			}
		}.toString();
	}
	public String insertDocument(Document document) {
		return new SQL() {
			{
				INSERT_INTO("document_inf");
				if(document.getTitle()!=null&&!document.getTitle().equals("")) {
					VALUES("title","#{title}");
				}
				if(document.getFilename()!=null&&!document.getFilename().equals("")) {
					VALUES("filename","#{filename}");
				}
				
				if(document.getUser()!=null&&document.getUser().getId()!=null&&document.getUser().getId()!=0) {
					VALUES("user_id","#{user.id}");
				}
				
			}
		}.toString();
	}
	public String updateDocument(Document document) {
		return new SQL() {
			{
				UPDATE("document_inf");
				if(document.getTitle()!=null&&!document.getTitle().equals("")) {
					SET("title=#{title}");
				}
				if(document.getFilename()!=null&&!document.getFilename().equals("")) {
					SET("filename=#{filename}");
				}
				
				
				if(document.getUser()!=null&&document.getUser().getId()!=null&&document.getUser().getId()!=0) {
					SET("user_id=#{user.id}");
				}
			}
		}.toString();
	}

}
