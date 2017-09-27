<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/img/1.jpg" rel="shortcut icon" />
    <link rel="stylesheet" href="/css/custom.css">
    <link rel="stylesheet" href="/css/regist.css">
    <link rel="stylesheet" href="/css/bootstrap.css" >
    <script src="/js/jquery.js"></script>
</head>
<body>
<jsp:include page="h_f/header.jsp" flush="true"></jsp:include>

<c:if test="${message!=null}">
  <script>
      alert("${message}");
  </script>
</c:if>
<div class="container" style="margin-top: 110px">
    <h1>用户注册</h1>
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <form:form action="/user/register" modelAttribute="postUser" method="post">
                <%-- mvc  modelAttribute="users" 是模型  = ‘传递的参数’ --%>
                <%--<div>--%>
                <%--<form:errors path="*" cssClass="error" element="p"/>--%>
                <%--</div>--%>

                <div>
                    <label>用户名</label>
                    <form:input path="username" Class="form-control"/>
                    <form:errors path="username" cssClass="error" element="p"/>
                </div>
                <div>
                    <label>邮箱</label>
                    <form:input path="email"   Class="form-control"/>
                    <form:errors path="email" cssClass="error" element="p"/>
                </div>
                <div>
                    <label>密码</label>
                    <form:input type="password" path="password" name="password" Class="form-control"/>
                    <form:errors path="password" cssClass="error" element="p"/>
                </div>
                <div>
                    <label>确认密码</label>
                    <input type="password" name="repassword" Class="form-control"/>
                    <c:if test="${remsg!=null}">
                    <p id="repassword_error" class="error">${remsg}</p>
                    </c:if>
                </div>

                <input type="submit" value="创建我的账号" Class="btn btn-primary" onsubmit="return check();"/>

            </form:form>
        </div>
    </div>
    <%@include file="h_f/footer.jsp" %>
</div>


<%--<button id="showmsg">点我显示某些东西</button>--%>
<style>
    input {
        margin-bottom: 15px;
        width: 100%;
        box-sizing: border-box;
        font-family: inherit;
    }
</style>

<script>
</script>
<script language="JavaScript">

    function check() {


        var pd1 = document.getElementById("password").value;
        var pd2 = document.getElementById("repassword").value;
        if (pd1 != pd2) {
            alert("两次密码不一致！");
            return false;
        }
        return true;
    }
</script>
</body>
</html>
