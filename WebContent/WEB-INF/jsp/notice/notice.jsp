<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<table width="100%" border="0">
     <tr>
        <td><center><font color="blue" size="45px">公告栏</font></center></td>
     </tr>
     <tr>
      <td height="40">
          <table width="100%" height="90%" border="1">
               <tr>
                  <td>id</td>
                  <td>公告名称</td>
                  <td>公告内容</td>
                  <td>创建时间</td>
                  <td>公告人</td>
                  <td>操作</td>
                  <td>预览</td>
               </tr>
          <c:forEach items="${requestScope.notices}" var="notice" >
               <tr>
                   <td>${notice.id }</td>
                   <td>${notice.title }</td>
                   <td>${notice.content }</td>
                   <td><f:formatDate value="${notice.createDate}" type="date" dateStyle="long"/></td>
                   <td>${notice.user.username}</td>
                   <td><a href="">修改</a></td>
                   <td><a href="${ctx}//notice/previewNotice?id=${notice.id}"  >预览</a></td>
               </tr>
          </c:forEach>
               
          
          </table>
      </td>
      </tr>
      <tr valign="top">
         <td align="center">
         <fkjava:pager pageIndex="${requestScope.pageModel.pageIndex }" pageSize="${requestScope.pageModel.pageSize }" 
         recordCount="${requestScope.pageModel.recordCount }" submitUrl="${ctx}/notice/selectNotice?pageIndex={0}"/>
        </td>
        </tr>
</table>

</body>
</html>