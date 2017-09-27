<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/12
  Time: 下午 07:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/bootstrap.css">
    <script src="/js/jquery.js"></script>
    <script src="/js/bootstrap.js"></script>
    <style>
        .nav .open > a, .nav .open > a:hover, .nav .open > a:focus{
            background-color: #080808;
        }
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

        .navbar-inverse {
            background: #222;
            border-color: #090909;
        }

        .navbar-fixed-top {
            top: 0;
            border-width: 0 0 1px;
            border-radius: 0;
            position: fixed;
            right: 0;
            left: 0;
            z-index: 1030;
        }

        .navbar {
            min-height: 50px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            display: block;
        }

        .container {
            width: 1170px;
            margin-right: auto;
            margin-left: auto;
            padding-left: 15px;
            padding-right: 15px;
        }

        #logo:hover {
            color: #fff;
            text-decoration: none;
        }

        #logo {
            float: left;
            margin-right: 10px;
            font-size: 1.7em;
            color: #fff;
            text-transform: uppercase;
            letter-spacing: -1px;
            padding-top: 9px;
            font-weight: bold;
        }

        .navbar-right {
            float: right !important;
            margin-right: -15px;
        }

        .nav {
            padding-left: 0;
            list-style: none;
        }



        .nav li {
            position: relative;
            display: block;
        }

        .navbar-nav li a {
            color: #9d9d9d;
            padding-top: 15px;
            padding-bottom: 15px;
            line-height: 20px;
        }
        .nav li a{
            position: relative;
            display: block;
            padding: 15px 15px;
        }



    </style>
</head>
<body>
<div class="navbar navbar-fixed-top navbar-inverse">
    <div class="container">
        <a id="logo" href="/">南方微博</a>
        <nav>
            <ul class="nav navbar-nav navbar-right">


                <c:choose>
                    <c:when test="${user!=null}">
                          <li><a href="/blog">首页</a></li>
                        <li><a href="/user/findUser">查找用户</a></li>
                        <li>
                            <div class="dropdown">
                                <a href="javascript:void(0)" class=" dropdown-toggle" id="dropdownMenu1"
                                        data-toggle="dropdown">
                                    ${user.username}
                                    <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                                    <li role="presentation">
                                        <a role="menuitem" tabindex="-1" href="/user/${user.userid}/findUser">个人主页</a>
                                    </li>
                                    <li role="presentation">
                                        <a role="menuitem" tabindex="-1" href="/user/edit">修改资料</a>
                                    </li>
                                    <li role="presentation">
                                        <a role="menuitem" tabindex="-1" href="/user/delogin">注销</a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/">首页</a></li>
                        <li><a href="/help">帮助</a></li>
                        <li><a href="/user/login">登录</a></li>
                    </c:otherwise>
                </c:choose>


            </ul>
        </nav>
    </div>
</div>

<%--<script>
    $(""){

    }
</script>--%>

</body>
</html>
