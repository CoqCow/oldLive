<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<html>
<body>
<h2>Hello World!</h2>
</body>

<form action="<c:url value="/json/getmylikes"/>" method="post">
    <input type="text" name="userId">
    <input type="submit" value="mylike">
</form>

<form action="<c:url value="/json/getfollowedNum"/>" method="post">
    <input type="text" name="userId">
    <input type="submit" value="ID">
</form>
<form action="<c:url value="/json/updateavatar"/>" method="post">
    UserID:
    <input type="text" name="userId">
    Avatar<input type="text" name="avatar">
    <input type="submit" value="avatar">
</form>
<form action="<c:url value="/json/updatenickName"/>" method="post">
    UserID:
    <input type="text" name="userId">
    nickName<input type="text" name="nickName">
    <input type="submit" value="nickName">
</form>
<form action="<c:url value="/json/updatePWD"/>" method="post">
    UserID:
    <input type="text" name="userId">
    Avatar<input type="text" name="PWD">
    <input type="submit" value="PWD">
</form>
<form action="<c:url value="/json/updateDescription"/>" method="post">
    UserID:
    <input type="text" name="userId">
    Description<input type="text" name="Description">
    <input type="submit" value="Description">
</form>
</html>
