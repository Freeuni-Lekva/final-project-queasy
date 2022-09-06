<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Create New Account</title>
</head>
<body>

<p>Please enter another name and password. <p style = "color : indianred "> Remember No '@' Allowed in username!</p></p>

  <form action = "addAccount" method = "post">
    <label> Username : </label>
    <input type = "text" name = "username" placeholder="Username"> <br>
    <label> Email : </label>
    <input type = "text" name = "email" placeholder="Mail"> <br>
    <label> Password : </label>
    <input type = "password" name = "password" placeholder="Password"><br>
    <input type = "submit" value="Create">1
</form>
<a href = "/login/login.jsp"> Login Page</a>
</body>
</html>
