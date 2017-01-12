<!DOCTYPE html>

<html lang="en">

<body>

<h3>用户列表</h3>
<#list sysUsers as sysUser>
id:${sysUser.id} <br/>
username:${sysUser.name} <br/>
password:${sysUser.password} <br/>
</#list>
msg = ${msg} <br/>

</body>

</html>