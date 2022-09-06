<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h1>Add Quiz</h1>
  <form action = "/CreateQuizServlet" method="get">
    <label>Name: </label>
    <input type="text" name = "name" placeholder="Enter Name">
    <br>

    <label>Description: </label>
    <input type="text" name = "description" placeholder="Enter Name">
    <br>

    <input type="submit">
  </form>
</body>
</html>
