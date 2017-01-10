<!DOCTYPE html>

<html lang="en">

<body>
<h3>用户详情</h3>
-----
<#if user ??>
username:${user.name} <br/>
password:${user.password} <br/>
    <#if msg ??>
        msg = ${msg} <br/>
    </#if>
</#if>
</body>

</html>