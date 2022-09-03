<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Create New Account</title>
</head>
<body>
  <%
    String name = request.getAttribute("failedName").toString();
    out.println("<h1> The Name  " + name + " is Already in Use</h1>");
  %>
<p>Please enter another name and password.</p>

  <form action = "addAccount" method = "post">
    <label> Username : </label>
    <input type = "text" name = "username" placeholder="Username"> <br>
    <label> Email : </label>
    <input type = "text" name = "email" placeholder="Mail"> <br>
    <label> Password : </label>
    <input type = "password" name = "password" placeholder="Password"><br>
    <input type = "submit" value="Create">1
</form>
<a href = "login.jsp"> Login Page</a>
</body>
</html>
