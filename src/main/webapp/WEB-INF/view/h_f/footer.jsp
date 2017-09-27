<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/12
  Time: 下午 08:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="footer">
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
</div>
</body>
<style>
    * {
        margin: 0;
        padding: 0;
    }

    a {
        text-decoration: none;
    }

    body {
        font-family: verdana, arial, helvetica, sans-serif;
        font-size: 13px;
    }

    .footer{
        margin-top: 45px;
        padding-top: 5px;
        border-top: 1px solid #eaeaea;
        color: #777;
    }
    .footer small{
        float: left;
        font-size: 85%;
    }
    
    .footer a{
        color: #555;
    }

    .footer ul{
        float: right;
        list-style: none;
        font-family: verdana, arial, helvetica, sans-serif;
        font-size: 13px;
        line-height: 18px;
        margin: 33px;
    }

    .footer ul li{
        float: left;
        margin-left: 15px;
        display: list-item;
        text-align: -webkit-match-parent;
    }

    .footer a{
        color: #555;
    }
</style>
</html>
