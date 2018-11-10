<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工表</title>
</head>
<body>
<table width="100%" border="0">

   <tr><td>
   <form action="${ctx}/employee/selectEmployee" method="post">
       <table border="0" width="100%" cellpadding="5" cellspacing="10">
         <tr>
                   <td>用户名：<input type="text" name="name"></td>
                  
                   <td><button>搜索</button></td>
         </tr>
       </table>
   </form>
   </td>
  </tr>
  
    <tr>
      <td><center><font color="blue" size="45px">员工表</font></center></td>
     <tr>
     <tr>
        <td height="40%">
             <table width="100%" height="90%" border="1">
                  <tr>
                     <td>id号</td>
                     <td>姓名</td>
                     <td>部门</td>
                     <td>工作</td>
                     <td>员工号</td>
                     <td>家庭地址</td>
                     <td>联系电话</td>
                     <td>性别</td>
                     <td>爱好</td>
                     <td>创建日期</td>
                     <td>操作1</td>
                     <td>操作2</td>
                   </tr>
               <c:forEach items="${requestScope.employees}" var="employee">
                  <tr>
                      <td>${employee.id}</td>
                      <td>${employee.name}</td>
                      <td>${employee.dept.name}</td>
                      <td>${employee.job.name}</td>
                      <td>${employee.cardId}</td>
                      <td>${employee.address}</td>
                      <td>${employee.tel}</td>
                      <td>
                         <c:choose>
                            <c:when test="${employee.sex==1}">
                             男
                            </c:when>
                            <c:otherwise>
                            女
                            </c:otherwise>
                         </c:choose>
                      </td>
                      <td>${employee.hobby }</td>
                      <td><f:formatDate value="${employee.creat_data}" />
                      <td><a href="${ctx}/employee/updateEmployee?flag=1&id=${employee.id}">修改</a></td>
                      <td><a href="${ctx}/employee/removeEmployee?ids=${employee.id}">删除</a></td>
                      
                    </tr>
               </c:forEach>
             
             </table>
        </td>
     </tr>
     <tr valign="top"><td align="center">
         <fkjava:pager 
             pageIndex="${requestScope.pageModel.pageIndex}" 
             pageSize="${requestScope.pageModel.pageSize}" 
             recordCount="${requestScope.pageModel.recordCount }" 
             style="digg"
             submitUrl="${ctx}/employee/selectEmployee?pageIndex{0}"/>
     </td></tr>

      
</table>

</body>
</html>