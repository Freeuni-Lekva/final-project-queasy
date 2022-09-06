<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create New Account</title>
</head>
<body>

    <h1>Create New Account</h1>
    <p>Please enter proposed name and password.</p>

    <form action = "/AddAccountServlet" method = "post">

        <label> Username : </label>
        <input type = "text" name = "username" placeholder="Username"> <br>
        <label> Email : </label>
        <input type = "text" name = "email" placeholder="Mail"> <br>
        <label> Password : </label>
        <input type = "password" name = "password" placeholder="Password"><br>
        <input type = "submit" value="Create">
    </form>
    <a href = "/login/login.jsp"> Login Page</a>
</body>
</html>
