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
    <title>南方微博</title>
    <link href="${pageContext.request.contextPath}/img/1.jpg" rel="shortcut icon" />
    <link rel="stylesheet" href="/css/custom.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/bootstrap.css">
   <%-- <link rel="stylesheet" href="/css/regist.css">
    <link rel="stylesheet" href="/css/view_login.css">--%>


</head>
<body>
<%@include file="h_f/header.jsp" %>
<div class="container" style="margin-top: 110px">

    <c:if test="${remsg!=null}"> <p class="alert alert-info ">${remsg}</p></c:if>
    <div class="container">
        <div class="text-center center jumbotron ">
            <h1>欢迎使用南方微博！</h1>

            <em>
                小<a href="https://github.com/s126">老鼠</a>，上灯台，偷油吃，下不来！发条微博叫人来。
            </em>

            <p style="margin-top: 10px"> <a class="btn btn-lg btn-primary " href="/user/regist">立刻注册！</a></p>
        </div>

        <%@include file="h_f/footer.jsp" %>
</div>
</div>
</body>

</html>
