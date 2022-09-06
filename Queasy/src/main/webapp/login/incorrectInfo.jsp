<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h1>Please Try Again</h1>
<p>Either your username or password is incorrect. Please try Again.</p>

<form action = "/LoginServlet" method = "post">
    <label> Username : </label>
    <input type = "text" name = "username" placeholder="Username"> <br>
    <label> Password : </label>
    <input type = "password" name = "password" placeholder="Password"><br>
    <input type = "submit" value="Login">
</form>
<a href = /login/createAccount.jsp> Create New Account</a>
</body>
</html>
