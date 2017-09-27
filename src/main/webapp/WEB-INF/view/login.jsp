<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/12
  Time: 下午 07:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/img/1.jpg" rel="shortcut icon" />
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/custom.css">
    <link rel="stylesheet" href="/css/regist.css">
    <link rel="stylesheet" href="/css/view_login.css">


</head>
<body>
<%@include file="h_f/header.jsp" %>
<div class="container" style="margin-top: 110px">
    <h1>登录</h1>

    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <form action="/user/loging" accept-charset="UTF-8" method="get">
                <div>
                    <%--<form:errors path="*" cssClass="error" element="p"/>--%>
                </div>
                <label>邮箱</label>
                <input class="form-control" type="email" name="email" id="session_email"/>
                <c:if test="${email_msg!=null}">
                    <p id="email_error" class="error">${email_msg}</p>
                </c:if>
                <label>密码</label>
                <a href="/password_resets/new">(忘记密码)</a>
                <input class="form-control" type="password" name="password" id="session_password"/>
                <c:if test="${pwd_msg!=null}">
                    <p id="password_error" class="error">${pwd_msg}</p>
                </c:if>
                <label class="checkbox inline">
                    <input name="session[remember_me]" type="hidden" value="0"/>
                    <input type="checkbox" value="1" name="remember_me]"/>
                    <span>记住密码</span>
                </label>
                <input type="submit" name="commit" value="登录" class="btn btn-primary" data-disable-with="登录"/>
            </form>
            <p>没有账号？ <a href="/users/add">点击注册！</a></p>
        </div>
    </div>
    <%@include file="h_f/footer.jsp" %>
</div>
</body>
<style>

</style>
</html>
