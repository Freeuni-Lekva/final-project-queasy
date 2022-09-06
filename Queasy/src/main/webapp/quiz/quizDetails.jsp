<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.Format" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="../css/main.css" rel="stylesheet" type="text/css">
    <c:set var="quiz" value="${applicationScope['CONTEXT_ATTRIBUTE_QUIZ_DAO'].getQuiz(id)}"></c:set>
    <c:set var="gamesByScore" value="${applicationScope['CONTEXT_ATTRIBUTE_GAME_DAO'].getAllGamesOrderedForScoring(id)}"></c:set>
    <jsp:useBean id="now" class="java.util.Date" />

    <title>Quiz ${quiz.getQuizName()}</title>
</head>
<body>

    <h1>Name : "${quiz.getQuizName()}"</h1>
    <p>Description : ${quiz.getDescription()}</p>
    <a href = "/QuizServlet?quizId=${quiz.getId()}"> TAKE QUIZ!</a><br>
    <c:set var="performancePercentage"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${ ((gamesByScore.stream().map(c -> c.getScore()).reduce(0,(a, b) -> a + b))/gamesByScore.size())/quiz.getQuestions().size() * 100}" /></c:set>

    <p>Performance Percentage : ${performancePercentage}%</p>
    <p>CREATOR : <a href = "#">${applicationScope['CONTEXT_ATTRIBUTE_QUIZ_DAO'].getCreator(id).getUserName()}</a></p>

    <div>
        <h2>High Performers of all time</h2>
        <table >
            <tr>
                <th>UserName</th>
                <th>Score</th>
            </tr>
        <c:forEach var = "game" items = "${gamesByScore}">
            <tr>
            <td> <a href = "">"${game.getUserName()}"</a></td>
            <td>"${game.getScore()}"</td>
            </tr>
        </c:forEach>
        </table>

        <h2>Top Performers of Day</h2>
        <table >
            <tr>
                <th>UserName</th>
                <th>Score</th>
            </tr>

            <c:forEach var = "game"
                       items = "${gamesByScore.stream().filter(c -> (now.getTime() - c.getStartDate().getTime()) < applicationScope['MILLISECONDS_IN_DAY']).toList()}">
                <tr>
                    <td> <a href = "">"${game.getUserName()}"</a></td>
                    <td>"${game.getScore()}"</td>
                </tr>
            </c:forEach>
        </table>

        <h2>Last taken quizzes</h2>
        <table>
            <tr>
                <th>UserName</th>
                <th>Score</th>
                <th>End date</th>
            </tr>
            <c:forEach var = "game" items = "${gamesByScore.stream().sorted((e1,e2) -> (e2.getEndDate().compareTo(e1.getEndDate()))).toList()}">
                <tr>
                    <td> <a href = "">"${game.getUserName()}"</a></td>
                    <td>"${game.getScore()}"</td>
                    <td>${game.getEndDate().toString()}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>
