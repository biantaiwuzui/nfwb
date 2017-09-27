<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>${user.username}|南方微博</title>
    <link href="/img/1.jpg" rel="shortcut icon" />
    <script src="/js/jquery-1.11.0.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" >
    <link rel="stylesheet" href="/css/bootstrap.css" />
    <script src="/js/jquery.js"></script>
    <%--<link rel="stylesheet" href="/css/custom.css">--%>
    <style>
        ol{
            list-style: none;
        }
        #commit{
            width: 300px;
            margin: 10px 0;
        }
        /*.post_picture{
            width: 500px;
            height:300px;
        }*/
    </style>

</head>
<body>


<p>
    <%@include file="../h_f/header.jsp" %></p>
<div class="container" style="margin-top: 110px">
    <div class="row">
        <aside class="col-md-4">
            <section class="user_info">
                <a href="/user/${user.userid}/findUser">
                    <img alt="${user.username}" class="gravatar" src="/img/1.jpg"/>

                </a>
                <h1>${user.username == null? "" : user.username}</h1>
                <span><a href="/user/${user.userid}/findUser">查看主页</a></span>
                <span>共 ${user.posts.size()} 则微博</span>

            </section>
            <section class="stats">
                <div class="stats">
                    <a href="/user/${user.userid}/following">
                        <strong id="following" class="stat">
                            ${user.participant.size()}
                        </strong>
                        关注
                    </a>
                    <a href="/user/${user.userid}/followers">
                        <strong id="followers" class="stat">
                            ${user.fans.size()}
                        </strong>
                        粉丝
                    </a>
                </div>

            </section>
            <section class="post_form">
               <%-- <form enctype="multipart/form-data" action="/weibo/post"
                      method="post">--%>
                 <%--  <form:form class="new_weibo" id="new_weibo" enctype="multipart/form-data" action="/weibo/posts"
                              accept-charset="UTF-8" modelAttribute="weibo" method="post">

                       <div class="field">
                           <textarea placeholder="发布新微博..."  name="content" id="content"></textarea>
                       </div>
                       <input type="submit" name="commit" value="发布" class="btn btn-primary" data-disable-with="发布"/>
                       <span class="picture">
                         <img id="pic" src="" />
                        <input accept="image/jpeg,image/gif,image/png,image/jpg" type="file" name="file"
                               id="weibo_picture"/>
                    </span>
                   </form:form>--%>
                   <form name="frm" action="/blog/posts" method="post" enctype="multipart/form-data">
                       <div>
                         <textarea style="width: 300px;height: 100px" name="content" ></textarea>
                       </div>
                       <input type="submit" name="commit" id="commit" class="btn btn-primary" data-disable-with="发布" >
                       <div class="picture">



                        <input  accept="image/jpeg,image/gif,image/png,image/jpg" type="file" name="file"
                               id="weibo_picture"/>
                        </div>
                   </form>
                   <img id="pic" src="" />

                   <button id="photoCover" class="btn btn-success">
                       <span class="glyphicon glyphicon-camera "></span>&nbsp;
                       添加图片
                   </button>


                   <script type="text/javascript">
                    $("#photoCover").on("click",function () {

                        $("#weibo_picture").click();

                    });

                    $("#weibo_picture").hide();

                    $('#weibo_picture').on('change', function () {
                        var size_in_megabytes = this.files[0].size / 1024 / 1024;
                        if (size_in_megabytes > 5) {
                            alert('文件的最大限制是 5M, 请重新选择');
                        }
                        var objUrl = getObjectURL(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
                        if (objUrl) {
                            $("#pic").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
                        }
                    });



                    //建立一個可存取到該file的url
                    function getObjectURL(file) {
                        var url = null ;
                        if (window.createObjectURL!=undefined) { // basic
                            url = window.createObjectURL(file) ;
                        } else if (window.URL!=undefined) { // mozilla(firefox)
                            url = window.URL.createObjectURL(file) ;
                        } else if (window.webkitURL!=undefined) { // webkit or chrome
                            url = window.webkitURL.createObjectURL(file) ;
                        }
                        return url ;
                    }
                </script>

            </section>
        </aside>

        <div class="col-md-8">
            <h3>微博动态</h3>
            <%-- 循环这段 --%>
            <ol class="posts list-group">
                <c:forEach items="${user.posts}" var="p" varStatus="status">
                        <li class="list-group-item">
                            <a href="/user/${p.user.userid}/findUser">
                            <span class="user">
                                <a href="/user/${p.user.userid}/findUser">${p.user.username==null?p.user.userid:p.user.username}</a>
                            </span>
                            <p class="content"> ${p.content} </p>
                                <img  class="post_picture" src="${p.picture}"/></a>
                            <p class="timestamp"> ${p.sendtime}
                                <a name="${p.pid}" href="javascript:void(0)" onclick="deletePost(${p.pid})">delete</a>
                            </p>
                        </li>
             </c:forEach>
            </ol>
            <%-- 循环 --%>

        </div>


    </div>

    <script>
        function deletePost(id) {
            $.ajax({
                method: 'delete',
                url: '/blog/' + id,
                dataType: 'text',
                success: function (data) {
                    if (data == "success") {
                        alert("删除成功");
                        location.reload();
                    }
                }
            });
        }
    </script>

    <%@include file="../h_f/footer.jsp" %>
</div>
</body>
</html>