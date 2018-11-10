<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户查询</title>
</head>
<body>
<table width="100%" height="90%" border="0">
  <tr><td>
   <form action="${ctx}/user/selectUser" method="post">
       <table border="0" width="100%" cellpadding="5" cellspacing="10">
         <tr>
                   <td>用户名：<input type="text" name="username"></td>
                  
                   <td><button>搜索</button></td>
         </tr>
       </table>
   </form>
   </td>
  </tr>
   
   <tr>
   <td height="40">
   <table width="100%" border="1">
   <tr>
        <td>Id号</td>
        <td>登录名</td>
        <td>密码</td>
        <td>用户名</td>
        <td>状态</td>
        <td>创建时间</td>
        <td align="center">操作1</td>
       
   </tr>
   <c:forEach items="${requestScope.users}" var="user">
       <tr>
           <td>${user.id}</td>
           <td>${user.loginname}</td>
           <td>${user.password}</td>
           <td>${user.username}</td>
           <td>${user.status}</td>
           <td><f:formatDate value="${user.createDate}" type="date" dateStyle="long"/></td>
           <td align="center" width="60"><a href="${ctx}/user/updateUser?flag=1&id=${user.id}">修改</a></td>
           
        
       </tr>
   
   </c:forEach>
   </table>
   </td>
   </tr>

	  <tr valign="top"><td align="center" class="font3">
	  	 <fkjava:pager
	  	        pageIndex="${requestScope.pageModel.pageIndex}" 
	  	        pageSize="${requestScope.pageModel.pageSize}" 
	  	        recordCount="${requestScope.pageModel.recordCount}" 
	  	        style="digg"
	  	        submitUrl="${ctx}/user/selectUser?pageIndex={0}"/>
	  </td></tr> 
</table>
<div style="height:10px;"></div>
</body>
</html>