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
</head>
<body>
<form action="upload/testUpload" method="post" enctype="multipart/form-data">
<input type="file" name="upload">
    <input type="submit" value="upload">
</form>

<a href="upload/testUpload">click me</a>
</body>
</html>
