<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/20
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery.min.js"></script>
    <script>
        $(function () {
            $("#btn").click(
                function () {
                    $.ajax({
                        url: "user/testUser",
                        contentType: "application/json;charset=UTF-8",
                        data: '{"name":"testName","age":100}',
                        dataType: "json",
                        type: "post",
                        success: function (data) {
                            alert(data);
                            alert(data.name);
                            alert(data.age);
                        }
                    });
                }
            );
        });
    </script>
</head>
<body>
<button id="btn"> click me</button>
</body>
</html>
