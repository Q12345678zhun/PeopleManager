<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>插入员工</title>
</head>
<body>
<table width="100%" border="1">
    <tr>
       <td><font color="blue" font-size="45">插入员工</font></td>
    </tr>
    <tr valign="top"><td>
    <form action="${ctx}/employee/addEmployee?flag=2" method="post">
            <table width="100%" height="90%" border="0">
                <tr>
                  <td><label>姓名:</label></td>
                  <td><input type="text" name="name" size="20"></td>
                 </tr>
                 <tr>
                  <td><label>员工号:</label></td>
                  <td><input type="text" name="cardId" size="20"/></td>
                 </tr>
                 <tr>
                  <td><label>家庭住址:</label></td>
                  <td><input type="text" name="address" size="20"/></td>
                 </tr>
                 <tr>
                  <td><label>联系电话:</label></td>
                  <td><input type="text" name="tel" size="20"/></td>
                 </tr>
                 <tr>
                  <td><label>爱好:</label></td>
                  <td><input type="text" name="hobby" size="20"/></td>
                 </tr>
                 <tr>
                    <td>
                        <select name="sex">
                            <option value="0">--请选择性别--</option>
                            <option value="1">男</option>
                            <option value="2">女</option>
                        </select>
                    </td>
                 </tr>
                 <tr>
                    <td>
                          <select name="job_id">
                               <option value="0">--请选择职位--</option>
                               <c:forEach items="${requestScope.jobs}" var="job">
                               <option value="${job.id}">${job.name}</option>
                               </c:forEach>
                          
                          </select>
                      </td>
                  </tr>
                  <tr>
                    <td>
                          <select name="dept_id">
                               <option value="0">--请选择部门--</option> 
                               <c:forEach items="${requestScope.depts}" var="dept">
                               <option value="${dept.id}">${dept.name}</option>
                               </c:forEach>
                          
                          </select>
                      </td>
                  </tr>
                  
                  
                  
                  <tr><td><button>添加</button></td></tr>
                 
                 
            
            </table>
    
    </form>
    </td></tr>


</table>

</body>
</html>