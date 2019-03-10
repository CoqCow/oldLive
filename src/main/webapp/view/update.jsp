<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2017/9/10
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新user表</title>
</head>
<body>
<form name="reg" action="/web/json/updateAll" method="post">
    组织名称： <input name="organizationName" value="${requestScope.user.organizationName}" type="text"><br>
    姓名：<input name="name" type="text" value="${requestScope.user.name}"><br>
    学院：<input name="college" type="text" value="${requestScope.user.college}"><br>
    昵称： <input name="nickName" type="text" value="${requestScope.user.nickName}"><br>
    头像：<input name="avatar" type="text" value="${requestScope.user.avatar}"><br>
    个性签名：<input name="signature" type="text"value="${requestScope.user.signature}"><br>
    用户Id：<input name="userId" type="text" value="${requestScope.user.userId}"  readonly="readonly " ><br>
    <input type="submit" value="更新"  >
</form>
</body>
</html>
