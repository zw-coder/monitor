<%--
  Created by IntelliJ IDEA.
  User: 18706
  Date: 2019/12/26
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统注册界面</title>
    <link rel="stylesheet" type="text/css" href="../js/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/css/register.css">
    <script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/user.js"></script>
</head>
<body>
<div class="register_container">
    <div class="register_box">
        <div id="title">注册系统账号</div>
        <div class="f1">
            <form id="ff" action="/user/register" method="post">
                <input class="easyui-validatebox" placeholder="账号" type="text" id="username" name="username"data-options="required:true,validType:'minLength[5]'"/><br>
                <input class="easyui-validatebox" placeholder="密码" type="password" id="password" name="password" data-options="required:true,validType:'minLength[5]'"/><br>
                <input class="easyui-validatebox" placeholder="再次输入密码" type="password" id="password_again"  required="required" validType="equals['#password']"/><br>
                <input class="easyui-validatebox" placeholder="邮箱" type="text" id="email" name="email" data-options="required:true,validType:'email'" /><br>
                <input type="button" id="register" onclick="submit2()" value="注册" />
            </form>
        </div>
        <div class="login">
            <p><a href="/user/tologin">登陆</a>&nbsp|&nbsp<a href="#">忘记密码</a></p>
        </div>
    </div>
</div>
</body>
</html>
