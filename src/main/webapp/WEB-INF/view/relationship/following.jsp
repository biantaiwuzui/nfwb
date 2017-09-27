<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我关注的人</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/img/1.jpg" rel="shortcut icon" />
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/web-inf_index.css">

    <link rel="stylesheet" href="/css/showcss.css">
    <link rel="stylesheet" href="/css/posts_index.css">
    <link rel="stylesheet" href="/css/custom.css">
    <link rel="stylesheet" href="/css/following.css">
    <script src="/js/jquery-1.11.0.min.js" ></script>
    <style>
        .user_avatars a:hover{
            background-color: #FFF;
        }
    </style>
</head>
<body>
<div><%@include file="../h_f/header.jsp" %></div>
<div class="container" style="margin-top: 90px">
    <div class="row">
        <aside class="col-md-4">
            <section class="user_info">
                <img alt="${userInfo.username}" class="gravatar" src="${userInfo.face}" />
                <h1>${userInfo.username}</h1>
                <span><a href="/user/${userInfo.userid}/findUser">查看主页</a></span>
                <span><b>微博</b> 1</span>
            </section>
            <section class="stats">
                <div class="stats">
                    <a href="/user/${userInfo.userid}/following">
                        <strong id="following" class="stat">
                            ${userInfo.participant.size()}
                        </strong>
                        关注
                    </a>
                    <a href="/user/${userInfo.userid}/followers">
                        <strong id="followers" class="stat">
                            ${userInfo.fans.size()}
                        </strong>
                        粉丝
                    </a>
                </div>

                <div class="user_avatars">
                    <c:forEach items="${userInfo.participant}" var="r">
                        <a href="/user/${r.by_user.userid}/findUser">
                            <img alt="${r.by_user.username}" style="width: 30px;height: 30px;" class="gravatar" src="${r.by_user.face}" />
                        </a>
                    </c:forEach>
                </div>
            </section>
        </aside>
        <div class="col-md-8">
            <h3>关注的人</h3>
            <ul class="users follow">
                <c:forEach items="${userInfo.participant}" var="r">
                    <li style="list-style-type:none;">
                        <img alt="${r.by_user.username}" class="gravatar" src="${r.by_user.face}" />
                        <a href="/user/${r.by_user.userid}/findUser">${r.by_user.username}</a>
                    </li>
                </c:forEach>
            </ul>

        </div>
    </div>





    <div><%@include file="../h_f/footer.jsp" %></div>
</div>
</body>
</html>
