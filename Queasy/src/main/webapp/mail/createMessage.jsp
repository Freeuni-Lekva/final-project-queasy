<%--
  Created by IntelliJ IDEA.
  User: bachi
  Date: 09.09.22
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
    <jsp:include page="/main/header.jsp"/>

    <form action = "/CreateMessageServlet" method = "post">
        <label>To: </label>
        <input type = "text" placeholder="Receiver..." name = "receiver">
        <br>
        <label>Subject: </label>
        <input type = "text" placeholder="Subject..." name = "subject">
        <br>
        <label>Message</label>
        <br>
        <textarea rows = "5" cols = "60" name = "message" placeholder="Message..."></textarea>
        <br>
        <input type = "submit" value = "Send message" name = "send">
        <br>
    </form>
</body>
</html>
