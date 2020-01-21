<%--
  Created by IntelliJ IDEA.
  User: hr
  Date: 2019/12/13
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>好友信息发布</title>
  </head>
  <body>

<form action="${pageContext.request.contextPath}/uploadAction!publish" method="post" enctype="multipart/form-data">
  编号：<input type="text" name="id" /><br/>
  姓名：<input type="text" name="name" /><br/>
  年龄：<input type="text" name="age" /><br/>
  性别：<input type="radio" value="男" name="gender" />男<input type="radio" value="女" name="gender" />女<br/>
  头像：<input type="file" name="photo" /><br/>
  <input type="submit" value="上传" /><br/>
</form>


  </body>
</html>
