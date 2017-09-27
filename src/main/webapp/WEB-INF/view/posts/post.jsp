<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/13
  Time: 下午 08:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${userInfo.username}|微博详情</title>
    <link href="/img/1.jpg" rel="shortcut icon" />
    <style>
        body{
            background: ghostwhite;
        }
        #body{
            margin: 100px;
        }
      /*  .post_picture{
            width: 500px;
            height: 300px;
        }*/



    </style>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/posts_index.css">
    <link rel="stylesheet" href="/css/bootstrap.css">
</head>

<div><%@include file="../h_f/header.jsp" %></div>
<div id="body" class="container">
    <div class="row">
        <aside class="col-md-3">
            <section>
                <h1>
                    <img alt="${userInfo.username}" class="gravatar" src="${userInfo.face}" />
                    ${userInfo.username}
                </h1>
            </section>
            <section class="stats">
                <div class="stats">
                    <a href="/user/${userInfo.userid}/following">
                        <strong id="following" class="stat">
                            ${userInfo.participant.size()}
                        </strong>
                        关注
                    </a>
                    <a href="/user/${userInfo.userid}/followers" >
                        <strong id="followers" class="stat">
                            ${userInfo.fans.size()}
                        </strong>
                        粉丝
                    </a>
                </div>

            </section>
            <section style="width: 50%;">
                <c:if test="${user.userid!=userInfo.userid}">
                <div id="follow_form">
                    <c:choose>
                    <c:when test="${relations.state==0&&relations!=null}">
                    <form  action="/relation/cancle" method="post">
                       <%-- <input name="utf8" type="hidden" value="&#x2713;" />--%>
                        <input type="hidden" name="firend_id" value="${userInfo.userid}" />
                       <%-- <div><input type="hidden" name="followed_id" id="followed_id" value="1" /></div>--%>
                        <input type="submit" name="commit" value="取消关注" class="btn btn-default" data-disable-with="取消关注" />
                    </form>
                    </c:when>
                    <c:otherwise>
                        <form class="new_relationship" id="new_relationship" action="/relation" accept-charset="UTF-8" data-remote="true" method="post">
                                <%-- <input name="utf8" type="hidden" value="&#x2713;" />--%>
                            <input type="hidden" name="firend_id" value="${userInfo.userid}" />
                                <%-- <div><input type="hidden" name="followed_id" id="followed_id" value="1" /></div>--%>
                            <input type="submit" name="commit" value="关注" class="btn btn-primary" data-disable-with="关注" />
                        </form>
                    </c:otherwise>
                    </c:choose>
                </div>
                </c:if>
            </section>
        </aside>
        <div class="col-md-9">
            <h3>微博 (${userInfo.posts.size()})</h3>
            <ol class="posts">
                <c:forEach items="${userInfo.posts}" var="p">

                <li id="post-17">
                    <a href="/user/${userInfo.userid}/findUser"><img alt="${userInfo.username}" class="gravatar" src="${userInfo.face}" /></a>
                    <span class="user"><a href="/user/${userInfo.userid}/findUser">${userInfo.username}</a></span>
                    <span class="content">${p.content}</span>
                    <span class="picture"><img src="${p.picture}" class="post_picture img-responsive  img-thumbnail" /></span>
                    <span class="timestamp">${p.sendtime}</span>
                </li>
                </c:forEach>


            </ol>

        </div>
    </div>
</div>
<%@include file="../h_f/footer.jsp" %>
<c:if test="${fmsg!=null}">
    <script>
        alert("${fmsg}");
    </script>
    <c:remove var="fmsg"></c:remove>
</c:if>
</body>
</html>
