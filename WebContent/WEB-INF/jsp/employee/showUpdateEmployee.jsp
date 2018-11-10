<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改员工信息</title>
</head>
<body>
<table width="100%" border="0">
     <tr>
       <td><center><font color="blue" size=45">修改员工信息</font></center></td>
     </tr>
     <tr>
       <td>
   <form action="${ctx}/employee/updateEmployee?flag=2&id=${employee.id}" method="post">
     <table width="100%" height="90%" border="1" align="center">
    <tr>
       <td><label>姓名：</label></td>
       <td><input type="text"  name="name" value="${employee.name}"/></td>
    </tr>
    <tr>
       <td><label>部门号：</label></td>
       <td><input type="text"  name="dept_id" value="${employee.dept.id}"/></td>
    </tr>
    <tr>
       <td><label>工作职位：</label></td>
       <td><input type="text"  name="job_id" value="${employee.job.id}"/></td>
    </tr>
    <tr>
       <td><label>员工号：</label></td>
       <td><input type="text"  name="cardId" value="${employee.cardId}"/></td>
    </tr>
    <tr>
       <td><label>地址：</label></td>
       <td><input type="text"  name="address" value="${employee.address}"/></td>
    </tr>
    <tr>
       <td><label>手机号：</label></td>
       <td><input type="text"  name="tel" value="${employee.tel}"/></td>
    </tr>
    <tr>
       <td><label>性别：</label></td>
       <td><input type="text"  name="sex" value="${employee.sex}"/></td>
    </tr>
    <tr>
       <td><label>爱好：</label></td>
       <td><input type="text"  name="hobby" value="${employee.hobby}"/></td>
    </tr>
    <tr>
       <td><Button>修改</Button></td>
     </tr>
   </table>
       
</form>
</td>
</tr>
</table>

</body>
</html>