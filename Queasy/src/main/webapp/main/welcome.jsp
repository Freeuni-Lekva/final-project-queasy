<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:set var="quizzes" value="${applicationScope['CONTEXT_ATTRIBUTE_QUIZ_DAO'].getAllQuizzes()}"></c:set>
    <link href="../css/main.css" rel="stylesheet" type="text/css">


</head>
<body>
 <nav class = "header"><jsp:include page="/main/header.jsp"/></nav>
<%--    <%--%>
<%--        if (session.getAttribute("username") != null){--%>
<%--            String name = request.getSession().getAttribute("username").toString();--%>
<%--            out.println("<h1> Welcome " + name + "</h1>");--%>
<%--        }--%>

<%--    %>--%>
    <c:forEach var = "quiz" items = "${quizzes}">

        <a href = "${pageContext.request.contextPath}/QuizDetailsServlet?quizId=${quiz.getId()}">"${quiz.getQuizName()}"</a><br>
    </c:forEach>



</body>
</html>
