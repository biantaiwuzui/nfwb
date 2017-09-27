<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/8
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
/*modelAttribute="user"*/
<form:form action="/regist" modelAttribute="user">
    <ul class="errorTip">
        <form:errors path="*" cssClass="error" element="li" />
    </ul>
    <div>
        <form:input path="username" />
        <form:errors path="username" cssClass="error" element="div" />
    </div>
    <div>
        <form:input type="number" path="password" />
        <form:errors path="password" cssClass="error" element="p" />
    </div>
    <div>
        <form:input path="email" />
        <form:errors path="email" cssClass="error" element="p" />
    </div>
    <input type="submit" value="注册" />
</form:form>
</body>
</html>
