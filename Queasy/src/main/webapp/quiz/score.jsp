<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Score</title>
</head>
<body>
<jsp:include page="/main/header.jsp"/>
<c:set var="hours"><fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${(endTime.getTime() - startTime.getTime()) / (60000 * 60)}" /></c:set>
<c:set var="minutes"><fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${(endTime.getTime() - startTime.getTime()) / (3600000) }" /></c:set>
<c:set var="seconds"><fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${(endTime.getTime() - startTime.getTime()) / 1000 }" /></c:set>

    <h1> Congratulations! Your score is : ${score}</h1>
    <h2>Time : ${hours} HH ${minutes} MM ${seconds} SS</h2>
    <h2>Start Time : ${startTime.toString()}</h2>
    <h2>End Time : ${endTime.toString()}</h2>

</body>
</html>
