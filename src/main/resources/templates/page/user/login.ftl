<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
</head>
<body>
<#if msg ??>
错误信息：<h4 th:text="${msg}"></h4>
</#if>
<form action="/users/login" method="post">
    <p>账号：<input type="text" name="sysUser.username" value="admin"/></p>
    <p>密码：<input type="text" name="sysUser.password" value="123456"/></p>
    <p><input type="submit" value="登录"/></p>
</form>
</body>
</html>