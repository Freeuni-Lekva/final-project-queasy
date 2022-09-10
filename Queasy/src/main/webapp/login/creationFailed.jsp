<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Create New Account</title>
</head>
<body>
<jsp:include page="/css/start.jsp"/>

<div class = "center">
  <p>Please enter another name and password. </p>
  <p style = "color : indianred "> Remember No '@' Allowed in username</p>
  <p style = "color : indianred ">Email must contain '@'</p>
  <p style = "color : indianred ">Username length must be more than 3</p>

  <form action = "/AddAccountServlet" method = "post">
      <label> Username : </label>
      <input type = "text" name = "username" placeholder="Username" required> <br>
      <label> Email : </label>
      <input type = "text" name = "email" placeholder="Mail" required> <br>
      <label> Password : </label>
      <input type = "password" name = "password" placeholder="Password" required><br>
      <input type = "submit" value="Create">
    </form>
  <a href = "/"> Login Page</a>
</div>
</body>
</html>
