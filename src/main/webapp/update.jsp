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
<form name="reg" action="json/updateAll" method="post">
   组织名称： <input name="organizationName" type="text"><br>
    学号：<input name="number" type="text"><br>
    密码：<input name="password" type="text"><br>
    姓名：<input name="name" type="text"><br>
    学院：<input name="college" type="text"><br>
    昵称： <input name="nickName" type="text"><br>
    头像：<input name="avatar" type="text"><br>
    个性签名：<input name="signature" type="text"><br>
    手机号：<input name="phoneNumber" type="text"><br>
   是否主播：<input name="whetherAnchor" type="text"><br>
    关注个数：<input name="followNumber" type="text"><br>
    注册时间：<input name="registrationTime" type="text"><br>
    <input type="submit" value="更新"  >
</form>
user:${requestScope.user.userId}
</body>
</html>
