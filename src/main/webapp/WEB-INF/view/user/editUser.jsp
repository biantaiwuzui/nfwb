<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/22
  Time: 8:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改${user.username}资料</title>
    <link href="/img/1.jpg" rel="shortcut icon" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/bootstrap.css">
    <style>

        ul{
            list-style: none;
        }
    </style>


</head>
<body>
<%@include file="../h_f/header.jsp" %>
<div class="container" style="margin-top: 100px" >
    <h1 class="text-center">修改个人资料</h1>

    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <form class="edit_user" id="edit_user_32" action="/user/${user.userid}" accept-charset="UTF-8" method="post"><input name="utf8" type="hidden" value="&#x2713;" /><input type="hidden" name="_method" value="patch" /><input type="hidden" name="authenticity_token" value="5jZiqO6sLBdJKJvlKgo2n531s2+bsxgm2ynRC9cge/2Ci/Z5Q2L6D0otIOCaBGh7PwjA4UKMl9UZIxcwlMmgXQ==" />


                <label for="user_email">邮箱</label>
                <input class="form-control" readonly="readonly" type="email" value="741940091@qq.com" name="user[email]" id="user_email" />

                <label for="user_name">用户名</label>
                <input class="form-control" type="text" value="666" name="user[name]" id="user_name" />


                <label for="user_password">密码</label>
                <input class="form-control" type="password" name="user[password]" id="user_password" />

                <label for="user_password_confirmation">密码确认</label>
                <input class="form-control" type="password" name="user[password_confirmation]" id="user_password_confirmation" />

                <input type="submit" name="commit" value="保存更改" class="btn btn-primary" data-disable-with="保存更改"  style="margin-top: 10px"/>
            </form>
            <div class="gravatar_edit" style="margin-top: 10px">
                <img alt="666" class="gravatar" src="https://secure.gravatar.com/avatar/942cb59c4123a0e39995962556add78e?s=80" style="margin-top: 10px" />
                <a href="http://gravatar.com/emails" target="_blank" style="margin-top: 10px">更换头像</a>
            </div>
        </div>
    </div>

<%@include file="../h_f/footer.jsp" %>
</body>
</html>
