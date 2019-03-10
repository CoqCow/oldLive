<%--
  Created by IntelliJ IDEA.
  User: whtk210
  Date: 2017-07-20
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>观看表操作!</h2>

<h3>观看<h3><br>
<form name="watch" action="json/watchAnchor" method="post">
    直播id：<input name="liveId" type="text">
    用户id：<input name="userId" type="text">
    <input type="submit" value="提交"  ><br>
</form>
    <h3>取消观看<h3><br>
<form name="diswatch" action="json/diswatchAnchor" method="post">
    直播id：<input name="liveId" type="text">
    用户id：<input name="userId" type="text">
    <input type="submit" value="提交"  >
</form>

</body>

</html>

