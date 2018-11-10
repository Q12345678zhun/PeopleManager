<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户修改</title>
</head>
<body>
<table width="100%" border="0">
   <tr height="40">
       <td>
           <form action="${ctx}/user/updateUser"  id="userForm" method="post">
             <input type="hidden" name="flag" value="2">
			<input type="hidden" name="id" value="${user.id }"> 
               <table width="90%"  border="1">
                  <tr>
                     <td><label>登录名:</label></td>
                     <td> <input type="text" name="loginname" value="${user.loginname}"/></td>
                  </tr>
                  <tr>
                     <td><label>密码:</label></td>
                     <td><input type="text" name="password" value="${user.password}"/></td>
                  </tr>
                  <tr>
                     <td><label>用户名:</label></td>
                     <td><input type="text" name="username" value="${user.username}"/></td>
                  </tr>
                  <tr>
                     <td><label>状态:</label></td>
                     <td><input type="text" name="status" value="${user.status}"/></td>
                  </tr>
                  <tr>
                      <td>
                      <Button>修改</Button>
                      </td>
                  </tr>
                
               
               </table>
           
           </form>
       </td>
   </tr>


</table>

</body>
</html>