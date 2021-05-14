<%--
  Created by IntelliJ IDEA.
  User: Ruchir Dixit
  Date: 13-05-2021
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Logged In</title>
</head>
<body>
<h3>
  Hi, <%= request.getAttribute("user")%>, Login Successful!
</h3>
</body>
</html>
