<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        if (session.getAttribute("username") == null){
            response.sendRedirect("login.jsp");
        }
        else {
            String titleName = request.getAttribute("name").toString();
            out.println("<title> Welcome " + titleName + "</title>");
        }
    %>

</head>
<body>
    <%
        if (session.getAttribute("username") != null){
            String name = request.getAttribute("name").toString();
            out.println("<h1> Welcome " + name + "</h1>");
        }
    %>

</body>
</html>
