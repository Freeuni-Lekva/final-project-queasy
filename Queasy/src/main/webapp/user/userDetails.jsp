<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <fmt:parseNumber var = "idInt" type = "number" value = "${param.id}" />

    <c:choose>
    <c:when test="${not empty param.id}">
      <c:set var="currUser" value="${applicationScope['CONTEXT_ATTRIBUTE_USER_DAO'].getUser(idInt)}"></c:set>

    </c:when>
    <c:otherwise>
        <c:set var="currUser" value="${loginedUser}"></c:set>
    </c:otherwise>
    </c:choose>
    <c:set var="gamesByScore" value="${applicationScope['CONTEXT_ATTRIBUTE_GAME_DAO'].getAllGamesOf(currUser.getUserName())}"></c:set>

    <c:set var="gamesByScore" value="${applicationScope['CONTEXT_ATTRIBUTE_GAME_DAO'].getAllGamesOf(currUser.getUserName())}"></c:set>
    <c:set var="following" value="${applicationScope['CONTEXT_ATTRIBUTE_FOLLOWING_DAO']}"></c:set>

    <c:set var="friends" value="${following.getFriendsOf(currUser.getUserName())}"></c:set>
    <c:set var="myFriends" value="${following.getFriendsOf(loginedUser.getUserName())}"></c:set>

    <c:set var="received" value="${following.getReceivedRequestsOf(loginedUser.getUserName())}"></c:set>
    <c:set var="sent" value="${following.getSentRequestsOf(loginedUser.getUserName())}"></c:set>

</head>
<body>
    <jsp:include page="/main/header.jsp"/>


    <c:choose>
        <c:when test="${currUser.getUserName().equals(loginedUser.getUserName())}">
            <h1> Hello ${loginedUser.getUserName()}</h1>
        </c:when>
        <c:otherwise>
            <h1>UserName: ${currUser.getUserName()}</h1>
        </c:otherwise>
    </c:choose>

    <h2>Top 3 Scores</h2>
    <table>
        <tr>
            <th>SCORE</th>
            <th>Quiz</th>
        </tr>

        <c:forEach var = "game" items = "${gamesByScore.stream().sorted((e1,e2) -> (e2.getScore().compareTo(e1.getScore()))).limit(3).toList()}">
            <tr>
                <td> ${game.getScore()}</td>
                <td><a  href = "/QuizDetailsServlet?quizId=${game.getQuizId()}">${game.getQuizId()}</a></td>
            </tr>
        </c:forEach>
    </table>

        <c:choose>
            <c:when test="${!currUser.getUserName().equals(loginedUser.getUserName()) }">
                <form action = "/UserDetailsServlet" method="get">
                <c:choose>

                    <c:when test="${myFriends.contains(currUser)}">
                        <input type="hidden" value="${idInt}" name="id"/>
                        <input  style = "background-color : red;"  type="submit" value="${"unfriend"}" name="request"/>
                    </c:when>
                    <c:when test="${sent.contains(currUser)}">
                        <input type="hidden" value="${idInt}" name="id"/>
                        <input  style = "background-color : red;"  type="submit" value="${"unsend_request"}" name="request"/>
                    </c:when>
                    <c:when test="${received.contains(currUser)}">
                        <input type="hidden" value="${idInt}" name="id"/>
                        <input  style = "background-color : mediumseagreen;"  type="submit" value="${"accept_request"}" name="request"/>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" value="${idInt}" name="id"/>
                        <input  style = "background-color : mediumseagreen;"  type="submit" value="${"send_request"}" name="request"/>
                    </c:otherwise>
                </c:choose>
                </form>
            </c:when>
            <c:otherwise>
                <h2>My profile</h2>
            </c:otherwise>
        </c:choose>

        <h2>Friends</h2>
        <table>
            <tr>
                <th>UserName</th>
            </tr>
            <c:forEach var = "friend" items = "${friends.stream().sorted((e1,e2) -> (e1.getUserName().compareTo(e2.getUserName()))).toList()}">
                <tr>
                    <td> <a href = "/profile?id=${friend.getId()}">"${friend.getUserName()}"</a></td>
                </tr>
            </c:forEach>
        </table>


        <c:choose>
            <c:when test="${currUser.getUserName().equals(loginedUser.getUserName())}">
                <h2>Friend Requests</h2>
                <table>
                    <tr>
                        <th>UserName</th>
                    </tr>
                    <form action = "/UserDetailsServlet" method="get">
                    <c:forEach var = "friend_requests" items = "${received.stream().sorted((e1,e2) -> (e1.getUserName().compareTo(e2.getUserName()))).toList()}">
                        <tr>
                            <c:if test="${currUser.getUserName().equals(loginedUser.getUserName())}">
                                <input type="hidden" value="true" name="myPage"/>
                            </c:if>
                            <input type="hidden" value="${friend_requests.getId()}" name="id"/>
                            <td>
                                <a href = "/profile?id=${friend_requests.getId()}">"${friend_requests.getUserName()}"</a>
                                <input  style = "background-color : mediumseagreen;"  type="submit" value="${"accept_request"}" name="request"/>
                            </td>

                        </tr>
                    </c:forEach>
                    </form>
                </table>
            </c:when>
        </c:choose>


</body>
</html>
