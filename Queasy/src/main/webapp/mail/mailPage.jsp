<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
  <c:set var="receivedEmails" value="${applicationScope['CONTEXT_ATTRIBUTE_MAIL_DAO'].getReceivedMails(loginedUser.getUserName()).stream().sorted((e1,e2) -> (e2.getDate().compareTo(e1.getDate()))).toList()}"></c:set>
  <c:set var="sentEmails" value="${applicationScope['CONTEXT_ATTRIBUTE_MAIL_DAO'].getSentMails(loginedUser.getUserName()).stream().sorted((e1,e2) -> (e2.getDate().compareTo(e1.getDate()))).toList()}"></c:set>

  <title>Mail</title>
</head>
<body>
  <jsp:include page="/main/header.jsp"/>
  <div>
    <label>Received</label>
  <c:forEach var = "mail" items = "${receivedEmails}">
  <div onclick="location.href='/read?id=${mail.getId()}';" style="cursor: pointer;  border:1px solid black;">
    <p>From : ${mail.getFrom()}</p>
    <p>Subject : ${mail.getSubject()}</p>
    <p>Date : ${mail.getDate().toString()}</p>
    <p>Text : ${fn:substring(mail.getText(),0,30)}...</p>
  </div>
  </c:forEach>
  </div>

  <div>
    <label>Sent</label>
    <c:forEach var = "mail" items = "${sentEmails}">
      <div onclick="location.href='/read?id=${mail.getId()}';" style="cursor: pointer;  border:1px solid black;">
        <p>To : ${mail.getTo()}</p>
        <p>Subject : ${mail.getSubject()}</p>
        <p>Date : ${mail.getDate().toString()}</p>
        <p>Text : ${fn:substring(mail.getText(),0,30)}...</p>
      </div>
    </c:forEach>
  </div>

</body>
</html>
