<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.Format" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <link href="../css/main.css" rel="stylesheet" type="text/css">

    <title>Quiz ${currQuiz.getQuizName()}</title>
</head>
<body>
    <h1>Name : "${currQuiz.getQuizName()}"</h1>
    <p>Description : ${currQuiz.getDescription()}</p>
    <a href = "">CREATOR : ${creatorUser.getUserName()}</a>

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
            <c:forEach var = "game" items = "${lastDayGames}">
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
            </tr>
            <c:forEach var = "game" items = "${gamesByDate}">
                <tr>
                    <td> <a href = "">"${game.getUserName()}"</a></td>
                    <td>"${game.getScore()}"</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
