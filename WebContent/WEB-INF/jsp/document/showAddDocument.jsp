<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<title>上传文件</title>
</head>
<body>
<table width="100%" height="90%" border="1" cellpadding="10" cellspacing="0">
    <tr>
         <td><center><font color="blue" size="45">上传</font></center></td>
     </tr>
     <tr valign="top">
     <td>
     <form action="${ctx}/document/addDocument?flag=2" enctype="multipart/form-data" method="post">
           <table  width="100%" border="0" cellpadding="0" cellspacing="10">
               <tr>
                   <td><lable>文件标题：</lable></td>
                   <td><input type="text" name="title"/></td>
               </tr>               
                
                <tr>
                   <td><lable>上传者：</lable></td>
                   <td><input type="file" name="file"/></td>
               </tr>
               <tr>
                  <td><button>上传</button></td>
               </tr>
           
           </table>
     
     </form>
     </td></tr>

</table>


</body>
</html>