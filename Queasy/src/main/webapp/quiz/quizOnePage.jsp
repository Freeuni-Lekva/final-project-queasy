<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <c:set var="currQuiz" value="${applicationScope['CONTEXT_ATTRIBUTE_QUIZ_DAO'].getQuiz(quizId)}"></c:set>
    <title>Quiz</title>
    <link href="../css/main.css" rel="stylesheet" type="text/css">

</head>
<body>
<h1 class="question-header"> ${currQuiz.getQuizName()}</h1>
    <form action = "/ScorePageServlet" method = "post">
        <input type="hidden" name = "quizId" value = "${currQuiz.getId()}">
        <c:forEach var = "curr" items = "${currQuiz.getQuestions()}" varStatus="counter">
            <div class = "question">
                <p>Question ${counter.index} :</p>
            <c:choose>
                <c:when test="${curr.getQuestionType().name().equals(QUESTION_RESPONSE)}">
                    <p>Text : ${curr.getText()}</p>
                    <input type="text" placeholder="Enter answer" name="answer-${curr.getId()}">
                    <br />
                </c:when>
                <c:when test="${curr.getQuestionType().name().equals(PICTURE_RESPONSE)}">

                    <p>Text : ${curr.getText()}</p>
                    <div style="margin: 32px;">
                    <c:forEach var = "currImg" items = "${curr.getPictures()}">
                        <img src="${currImg.getPicture()}" alt="Something went wrong..." width = "200px">
                           <br>
                    </c:forEach>
                    </div>
                    <input type="text" placeholder="Enter answer" name="answer-${curr.getId()}">
                    <br />
                </c:when>
                <c:when test="${curr.getQuestionType().name().equals(FILL_IN_THE_BLANK) }">
                        <c:if test="${curr.getText().contains(FILL_BLANK_STRING)}">
                        <p>Text : ${curr.getText().substring(curr.getText().indexOf(FILL_BLANK_STRING))}
                            <input type="text" placeholder="Enter answer" name = "answer-${curr.getId()}">
                                ${curr.getText().substring(curr.getText().indexOf(FILL_BLANK_STRING) + FILL_BLANK_STRING.length(),curr.getText().length()-1)}
                        </p>
                        </c:if>
                        <c:if test="${!curr.getText().contains(FILL_BLANK_STRING)}">
                            <p>Text : ${curr.getText()}</p>
                            <p>${curr.getText()}</p>
                            <input type="text" placeholder="Enter answer" name = "${curr.getId()}">
                        <br />
                        </c:if>
                    </c:when>
                <c:when test="${curr.getQuestionType().name().equals(MULTIPLE_CHOICE)}">
                    <c:set var="answersForQuestions" value="${multipleChoiceAnswers.stream().filter(c -> c.getQuestionId() == curr.getId()).toList()}" />
                    <p>Text : ${curr.getText()}</p>
                    <c:forEach var = "currAnswer"
                               items = "${answersForQuestions}"
                               varStatus="innerCounter">

                        <input type="radio" name = "answer-${curr.getId()}" value="${currAnswer.getText()}">${currAnswer.getText()}
                        <br>
                    </c:forEach>
                    <br />
                </c:when>
                <c:otherwise>
                   <p>Question didn't load, error in question syntax</p>
                </c:otherwise>
            </c:choose>

            </div>

        </c:forEach>
        <input type="submit" value = "Submit quiz">
    </form>
</body>
</html>
