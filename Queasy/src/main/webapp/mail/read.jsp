<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bachi
  Date: 09.09.22
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Read</title>
    <fmt:parseNumber var = "idInt" type = "number" value = "${param.id}" />
    <c:set var="mail" value="${applicationScope['CONTEXT_ATTRIBUTE_MAIL_DAO'].getEmailById(idInt)}"></c:set>

</head>
<body>
  <jsp:include page="/main/header.jsp"/>
  <p>From: ${mail.getFrom()}</p><br>
  <p>To: ${mail.getTo()}</p><br>
  <p>Subject: ${mail.getSubject()}</p><br>
  <p>Text: ${mail.getText()}</p><br>
</body>
</html>
