<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/13
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="test/testSuccess">click me</a>

<form action="account/testAccount" method="post">
    user name:<input type="text" name="name"/><br>
    user age:<input type="text" name="age"><br>
    account:<input type="text" name="account.account"><br>
    manager:<input type="text" name="managerList[0].id"><br>
    manager:<input type="text" name="managerList[1].id"><br>
    testMap:<input type="text" name="testMap['test']"><br>
    <input type="submit" value="提交"><br>
</form>
</body>
</html>
