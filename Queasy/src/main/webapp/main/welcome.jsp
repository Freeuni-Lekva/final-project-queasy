<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        if (session.getAttribute("username") == null){
            response.sendRedirect("/login/login.jsp");
        }
    %>

</head>
<body>
 <jsp:include page="header.jsp"/>
    <%
        if (session.getAttribute("username") != null){
            String name = request.getSession().getAttribute("username").toString();
            out.println("<h1> Welcome " + name + "</h1>");
        }

    %>
    <c:forEach var = "quiz" items = "${quizzes}">

        <a href = "/quiz/quizDetails?quizId=${quiz.getId()}">"${quiz.getQuizName()}"</a><br>
    </c:forEach>



</body>
</html>
