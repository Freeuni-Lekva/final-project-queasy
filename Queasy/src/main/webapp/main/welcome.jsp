<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:set var="quizzes" value="${applicationScope['CONTEXT_ATTRIBUTE_QUIZ_DAO'].getAllQuizzes()}"></c:set>
    <c:set var="games" value="${applicationScope['CONTEXT_ATTRIBUTE_GAME_DAO'].getAllGames()}"></c:set>
    <link href="../css/main.css" rel="stylesheet" type="text/css">
    <title>Welcome</title>
</head>
<body>
<jsp:include page="/main/header.jsp"/>

    <c:forEach var = "quiz" items = "${quizzes}">
        <a href = "${pageContext.request.contextPath}/QuizDetailsServlet?quizId=${quiz.getId()}">"${quiz.getQuizName()}"</a><br>
    </c:forEach>





</body>
</html>
