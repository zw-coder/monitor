<%--
  Created by IntelliJ IDEA.
  User: 18706
  Date: 2019/12/26
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆界面</title>
    <link rel="stylesheet" type="text/css" href="/css/login.css">
    <script type="text/javascript" src="/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/user.js"></script>
</head>

<body>
<div class="login_container">
    <div class="login_box">
        <div class="logo">
            <div id="title">登录监控系统</div>
        </div>
        <div class="f1">
            <form id="ff" action="/user/login" method="post">
                <input class="easyui-validatebox"  placeholder="账号" type="text" id="username" name="username"/><br>
                <input class="easyui-validatebox" placeholder="密码" type="password" id="password" name="password"/><br>
                <input type="button" id="login" onclick="submit1()" value="登陆"/>
            </form>
        </div>
        <div class="register">
            <p><a href="/user/toregister">注册</a>&nbsp;|&nbsp;<a href="#">忘记密码</a></p>
        </div>
    </div>
</div>
</body>
</html>
