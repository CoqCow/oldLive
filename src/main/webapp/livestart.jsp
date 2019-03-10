<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2017/11/1
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>开始直播</title>

</head>
<body>
<body>
<form name="start" action="json/livestart" method="post">
    userId： <input name="userId" type="text"><br>
    liveName：<input name="liveName" type="text"><br>
    liveDescription：<input name="liveDescription" type="text"><br>
    liveType：<input name="liveType" type="text"><br>
    livePwd：<input name="livePwd" type="text"><br>
    liveUrl：<input name="liveUrl" type="text" value="rtmp://live.hkstv.hk.lxdns.com/live/hks"><br>
    screenShot：<input name="screenShot" type="text" value="live_01.png" readonly><br>
    <input type="submit" value="开始直播"  >
</form>

<h3>删除直播<h3><br>
    <form name="end" action="json/endlive" method="post">
        直播id：<input name="liveId" type="text">
        用户id：<input name="userId" type="text">
        <input type="submit" value="提交"  ><br>
    </form>
</body>
</body>
</html>
