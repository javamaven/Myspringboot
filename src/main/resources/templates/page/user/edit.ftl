<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
</head>
<body>
${msg}
<form action="/users/" method="post">
    <p>账号：<input type="text" name="user.name" value="admin"/></p>
    <p>密码：<input type="text" name="user.password" value="123456"/></p>
    <p><input type="submit" value="登录"/></p>
</form>
</body>
</html>