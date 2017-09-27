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
    <title>查找用户</title>
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
<div class="container ">
    <div class="text-center" style="margin-top: 100px">
        <h1 class="h1">发现更大世界，给你更多激情</h1>
        <p><small>这个页面应该包含搜索栏，还应该有各种好友推荐什么的。在这里简化为显示所有用户了。because i'm lazy.</small></p>
    </div>
    <br>

    <div class="pagination">
        <ul class="pagination">
            <li class="prev previous_page disabled"><a href="#">&laquo;</a></li>
            <li class="active"><a rel="start" href="/users?page=1">1</a></li>
            <li><a rel="next" href="/users?page=2">2</a></li>
            <li class="next next_page "><a rel="next" href="/users?page=2">&raquo;</a></li>
         </ul>
    </div>

    <ul class="users">
        <c:forEach items="${userList}" var="u">

        <li>
            <img alt="${u.username}" class="gravatar" src="${u.face}">
            <a href="/user/${u.userid}/findUser">${u.username}</a>
        </li>

        </c:forEach>


    <footer class="footer">
        <small>
            请在 <a href="https://github.com/s126">Github</a> 上关注我们
        </small>
        <nav>
            <ul>
                <li><a href="/about">关于</a></li>
                <li><a href="/contact">联系我们</a></li>
                <li><a href="#">招贤纳士</a></li>
            </ul>
        </nav>
    </footer>

    <pre class="debug_dump">--- !ruby/object:ActionController::Parameters
parameters: !ruby/hash:ActiveSupport::HashWithIndifferentAccess
  controller: users
  action: index
permitted: false
</pre>
</div>

<%@include file="../h_f/footer.jsp" %>
</body>
</html>
