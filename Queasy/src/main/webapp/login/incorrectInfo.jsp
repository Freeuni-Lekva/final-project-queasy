<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<jsp:include page="/css/start.jsp"/>

<div class = "center">
    <p>IncorrectInfo</p>
    <p>Please Try Again...</p>

    <form action = "/LoginServlet" method = "post">
        <label> Username : </label>
        <input type = "text" name = "username" placeholder="Username" required> <br>
        <label> Password : </label>
        <input type = "password" name = "password" placeholder="Password" required><br>
        <input type = "submit" value="Login">
    </form>

    <a href = /register> Create New Account</a>
</div>
</body>
</html>
