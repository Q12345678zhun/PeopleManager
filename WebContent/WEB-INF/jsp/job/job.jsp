<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工作查询</title>
</head>
<body>
<table  width="100%" height="90%" border="0">
   <tr>
      <td><center><font color="blue" size="45px">工作职位</font></center></td>
   </tr>
   
   <tr>
   <td height="40">
   <table width="100%" border="1" >
     <tr height="40">
         <td>id号</td>
         <td>工作名</td>
         <td>描述</td>
         <td>修改</td>
     </tr>
     <c:forEach items="${requestScope.jobs}" var="job">
      <tr>
         <td>${job.id}</td>
         <td>${job.name}</td>
         <td>${job.remark}</td>
         <td><a href="">修改</a></td>   
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
                  submitUrl="${ctx}/job/selectJob?pageIndex{0}"/>
                 
             </td>
       </tr>
 
</table>
<div style="height:10px;"></div>

</body>
</html>