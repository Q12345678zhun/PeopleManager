<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加公告</title>
</head>
<body>
<table width="100%" border="0">

    <tr>
       <td><center><font color="blue" size="45">添加公告</font></center></td>
    </tr>
    <tr vligin="top"><td>
    <form action="${ctx}/notice/addNotice?flag=2" method="post">
    <table width="50%" height="90%" border="0" align="center">
          <tr>
             <td ><lable>添加标题:</lable>
             <td><input type="text" name="title" size=20/></td>
          </tr>
          <tr>
             <td ><label >公告内容</label></td>
             <td><textarea rows="10" cols="60" name="content"></textarea></td>
           </tr>
          <tr><td align="center"><button >添加</button></td></tr>
    </table>
    </form>
</table>

</body>
</html>