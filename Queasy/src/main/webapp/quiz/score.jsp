<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Score</title>
</head>
<body>
<jsp:include page="/main/header.jsp"/>

    <h1> Congratulations! Your score is : ${score}</h1>
    <h2>Time : ${(endTime.getTime() - startTime.getTime()) / 60000} Minutes </h2>
    <h2>Start Time : ${startTime.toString()}</h2>
    <h2>End Time : ${endTime.toString()}</h2>

</body>
</html>
