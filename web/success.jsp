<%--
  Created by IntelliJ IDEA.
  User: hr
  Date: 2019/12/13
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SUCCESS</title>
</head>
<body>

<table>
    <thead>
        <tr>
            <th>编号</th><th>姓名</th><th>年龄</th><th>性别</th><th>头像</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>${id}</td>
            <td>${name}</td>
            <td>${age}</td>
            <td>${gender}</td>
            <td>
                <a href="${pageContext.request.contextPath}/uploadAction!download?fileName=${photoPath}" title="下载">
                    <img src="${pageContext.request.contextPath}/upload/${photoPath}" style="width: 60px;height: 60px;">
                </a>
            </td>
        </tr>
    </tbody>

</table>




</body>
</html>
