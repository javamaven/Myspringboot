<!DOCTYPE html>

<html lang="en">

<body>
<h3>用户详情</h3>
-----
<#if sysUser ??>
username:${sysUser.name} <br/>
password:${sysUser.password} <br/>
    <#if msg ??>
        msg = ${msg} <br/>
    </#if>
</#if>
</body>

</html>