<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:set var="quizDao" value="${applicationScope['CONTEXT_ATTRIBUTE_QUIZ_DAO']}"></c:set>
    <c:set var="userDao" value="${applicationScope['CONTEXT_ATTRIBUTE_USER_DAO']}"></c:set>
    <c:set var="games" value="${applicationScope['CONTEXT_ATTRIBUTE_GAME_DAO'].getAllGames()}"></c:set>


    <link href="../css/main.jsp" rel="stylesheet" type="text/css">
    <title>Welcome</title>
</head>
<body>
<jsp:include page="/main/header.jsp"/>
<jsp:include page="/css/home.jsp"/>

<h2>Top Scorers</h2>
    <table>
        <tr>
            <th>UserName</th>
            <th>QuizName</th>
            <th>Score</th>

        </tr>
        <c:forEach var = "game" items = "${games.stream().sorted((e1,e2) ->
                    ((e2.getScore()/quizDao.getAllQuestions(e2.getQuizId()).size()* 100).compareTo((e1.getScore()/quizDao.getAllQuestions(e1.getQuizId()).size() * 100)))).toList()}">
            <tr>
                <td><a href = "${pageContext.request.contextPath}/profile?id=${userDao.getUser(game.getUserName()).getId()}">${game.getUserName()}</a></td>
                <td><a href = "${pageContext.request.contextPath}/QuizDetailsServlet?quizId=${game.getQuizId()}">${quizDao.getQuiz(game.getQuizId()).getQuizName()}</a></td>
                <td>${game.getScore()} (${game.getScore()/quizDao.getAllQuestions(game.getQuizId()).size() * 100} %)</td>

            </tr>

        </c:forEach>
    </table>

<h2>All Quizzes</h2>
    <table>
        <tr>
            <th>QuizName</th>
            <th>Creator UserName</th>
            <th>Quiz Description</th>
        </tr>
    <c:forEach var = "quiz" items = "${quizDao.getAllQuizzes()}">
        <tr>
            <td><a href = "${pageContext.request.contextPath}/QuizDetailsServlet?quizId=${quiz.getId()}">${quiz.getQuizName()}</a><br></td>
            <td><a href = "${pageContext.request.contextPath}/profile?id=${quiz.getCreatorId()}">${userDao.getUser(quiz.getCreatorId()).getUserName()}</a></td>
            <td>${quiz.getDescription()}<br></td>
        </tr>
    </c:forEach>
    </table>

<h2>All Users</h2>
<table>
    <tr>
        <th>User names</th>
    </tr>
    <c:forEach var = "user" items = "${userDao.getAllUsers()}">
        <tr>
            <td><a href = "${pageContext.request.contextPath}/profile?id=${user.getId()}">${user.getUserName()}</a><br></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
