<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件下载</title>
</head>
<body>
<table width="100%" border="0">
     <tr>
        <td><center><font color="blue" size="45px">文件下载</font></center></td>
     </tr>
     <tr>
      <td height="40">
          <table width="100%" height="90%" border="1">
               <tr>
                  <td>id</td>
                  <td>文件标题</td>
                  <td>文件名</td>
                  <td>创建时间</td>
                  <td>上传者</td>
                  <td>操作</td>
               </tr>
          <c:forEach items="${requestScope.documents}" var="document" >
               <tr>
                   <td>${document.id }</td>
                   <td>${document.title }</td>
                   <td>${document.filename }</td>
                   <td><f:formatDate value="${document.createDate}" type="date" dateStyle="long"/></td>
                   <td>${document.user.username}</td>
                   <td><a href="${ctx}/document/download?id=${document.id}">下载</a></td>
                  
               </tr>
          </c:forEach>
               
          
          </table>
      </td>
      </tr>
      <tr valign="top">
         <td align="center">
         <fkjava:pager pageIndex="${requestScope.pageModel.pageIndex }" pageSize="${requestScope.pageModel.pageSize }" 
         recordCount="${requestScope.pageModel.recordCount }" submitUrl="${ctx}/document/selectDocument?pageIndex={0}"/>
        </td>
        </tr>
</table>


</body>
</html>