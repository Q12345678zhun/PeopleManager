package org.fkit.hrm.util.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/*
 * 分页标签
 */
public class PagerTag extends SimpleTagSupport{
    /*
     * 定义请求url中的占位常量
     */
	private static final String TAG="{0}";
	/**当前页码*/
	private int pageIndex;
	/*每页显示的数量*/
	private int pageSize;
	/*总记录条数*/
	private int recordCount;
	/*请求url page.action?pageIndex={0};*/
	private String submitUrl;
	private String style="sabrosus";
	private int totalPage=0;
	public void doTag() throws JspException,IOException{
		//定义它拼接最终的结果
		StringBuilder res=new StringBuilder();
		//定义它拼接中间的页码
		StringBuilder str=new StringBuilder();
		if(recordCount>0) {
			totalPage=(this.recordCount-1)/this.pageSize+1;
			/*
			 * 判断上一页或下一页需不需要加a标签
			 */
			if(this.pageIndex==1) {
				//点击上一页没有反应，因为有disable
				str.append("<span class='disable'>上一页</span>");
				//计算中间页码
				this.calcPage(str);
				//只有一页时
				if(this.pageIndex==totalPage) {
					//点击下一页没有反应，因为有disable
					str.append("<span class='disable'>下一页</span>");
				}else {
					String tempUrl=this.submitUrl.replace(TAG, String.valueOf(pageIndex+1));
					str.append("<a href=' "+tempUrl+"'>下一页</a>");
				}
				//当前页等于尾页的时候
			}else if(this.pageIndex==totalPage) {
				String tempUrl=this.submitUrl.replace(TAG, String.valueOf(pageIndex-1));
				str.append("<a href='"+tempUrl+"'>上一页</a>");
				this.calcPage(str);
				str.append("<span class='disable'>下一页</span>");
				
				
			}else {
				String tempUrl=this.submitUrl.replace(TAG, String.valueOf(pageIndex-1));
				str.append("<a href='"+tempUrl+"'>上一页</a>");
				this.calcPage(str);
				tempUrl=this.submitUrl.replace(TAG, String.valueOf(pageIndex+1));
				str.append("<a href='"+tempUrl+"'>下一页</a>");
				
			}
			/**
			 * 配节其他的信息用 res
			 */
			res.append("<table width='100%',align='center' style='font-size:13px;' class='"+style+"'>");
			res.append("<tr><td style='color:#0061de;margin-right:3px;padding-top:2px;text_decoration:none'>"+str.toString());
			res.append("跳转到&nbsp;&nbsp;<input type='text' id='pager_jump_size'/>");
			res.append("&nbsp;<input type='button' value='确定' id='pager_jump_btn'/>");
			res.append("</td></tr>");
			res.append("<tr align='center'><td style='font-size:13px;'><tr><td>");
			int startNum=(this.pageIndex-1)*this.pageSize+1;
			int endNum=(this.pageIndex==this.recordCount)?this.recordCount:this.pageIndex*this.pageSize;
			res.append("总共<font color='red'>"+this.recordCount+"</font>条记录，当前显示"+startNum+"-"+endNum+"条记录。");
			res.append("</td></tr>");
			res.append("<script type='text/javascript'>");
			res.append("document.getElementById('pager_jump_btn').onclick=functin(){");
			res.append("var page_size=document.getElementById('pager_jump_page_size').value;");
			res.append("if(!/^[1-9]\\d*$/.test(page_size)||page_size<1||page_size>"+this.totalPage+"){");
			res.append("alert('请输入[1-"+this.totalPage+"]之间的页码");
			res.append("}else{");
			res.append("var submit_url='"+this.submitUrl+"';");
			res.append("window.location=submit_utl.replace('"+TAG+"',page_size)");
			res.append("  }");
			res.append("}");
			res.append("</script>");
		}else {
			res.append("<table align='center'><tr><td>总共显示0条记录</td></tr></table>");
		}
		this.getJspContext().getOut().print(res.toString());
			
			
		}
	private void calcPage(StringBuilder str) {
		// TODO Auto-generated method stub
		if(this.totalPage<=11) {
			for(int i=1;i<=this.totalPage;i++) {
				if(pageIndex==i) {
					str.append("<span class='current'>"+i+"</span>");
				}else {
					String tempUrl=this.submitUrl.replace(TAG, String.valueOf(i));
					str.append("<a href='"+tempUrl+"'>"+i+"</a>");
				}
			}
		}else {
			if(this.pageIndex<=8) {
				for(int i=1;i<=10;i++) {
					if(this.pageIndex==i) {
						str.append("<span class='current'>"+i+"</span>");
					}else {
						String tempUrl=this.submitUrl.replace(TAG, String.valueOf(i));
						str.append("<a href='"+tempUrl+"'>"+i+"</a>");
				}
			}
				str.append("...");
				String tempUrl=this.submitUrl.replace(TAG, String.valueOf(this.totalPage));
				str.append("<a href='"+tempUrl+"'>"+this.totalPage+"</a>");
		}
			else if(this.pageIndex+8>=this.totalPage){
				String tempUrl=this.submitUrl.replace(TAG, String.valueOf(1));
				str.append("<a href='"+tempUrl+"'>"+1+"</a>");
				str.append("...");
				for(int i=totalPage-10;i<=this.totalPage;i++) {
					if(this.pageIndex==i) {
						str.append("<spqn class='current'>"+i+"</span>");
					}else {
						 tempUrl=this.submitUrl.replace(TAG, String.valueOf(i));
						str.append("<a href='"+tempUrl+"'>"+i+"</a>");
					}
				 }
				}else {
					String tempUrl=this.submitUrl.replace(TAG, String.valueOf(1));
					str.append("<a href='"+tempUrl+"'>1</a>");
					str.append("...");
					for(int i=this.pageIndex-4;i<=pageIndex+4;i++) {
						if(this.pageIndex==i) {
							str.append("<spqn class='current'>"+i+"</span>");
						}else {
							tempUrl=this.submitUrl.replace(TAG,String.valueOf(i));
							str.append("<a href='"+tempUrl+"'>"+i+"</a>");
						}
					}
					str.append("...");
					tempUrl=this.submitUrl.replace(TAG, String.valueOf(this.totalPage));
					str.append("<a href='"+tempUrl+"'>"+this.totalPage+"</a>");
						
				
				
			}
			
		
	}
	
	}
	
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	
	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}
	
	public void setStyle(String style) {
		this.style = style;
	}
	

}
