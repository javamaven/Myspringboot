<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
</head>
<body>
<form action="/login" method="post">
    <p>账号：<input type="text" name="username" value="admin"/></p>
    <p>密码：<input type="text" name="password" value="123456"/></p>
    <p><input type="submit" value="登录"/></p>
</form>

<#if msg ??>
错误提示：<h4 >${msg}</h4>
</#if>
</body>
</html>