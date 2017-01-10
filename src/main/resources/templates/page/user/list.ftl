<!DOCTYPE html>

<html lang="en">

<body>

<h3>用户列表</h3>
<#list users as user>
id:${user.id} <br/>
username:${user.name} <br/>
password:${user.password} <br/>
</#list>
msg = ${msg} <br/>

</body>

</html>