<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Title</title>
  <script>
    function removeAdditional(num) {
      let container = document.getElementById("additionalInfo-" + num);
      while (container.hasChildNodes()) {
        container.removeChild(container.lastChild);
      }
    }

    function fillBlankText(num) {
      let container = document.getElementById("additionalInfo-" + num);
      while (container.hasChildNodes()) {
        container.removeChild(container.lastChild);
      }
      let p = document.createElement("p");
      p.innerHTML = "add \"$___$\" (without brackets) where you want to be a blank line ";
      container.appendChild(p);
    }

    function pictureResponse(num) {
      let container = document.getElementById("additionalInfo-" + num);
      while (container.hasChildNodes()) {
        container.removeChild(container.lastChild);
      }
      let input = document.createElement("input");
      input.type = "text";
      input.placeholder = "Add image URL";
      input.name = "picture-" + num;
      input.setAttribute("required","true");
      container.appendChild(input);
    }

    function addAnswerField(num) {

      let number = document.getElementById("answerNumber" + num).value;
      let container = document.getElementById("answerContainer-" + num);
      while (container.hasChildNodes()) {
        container.removeChild(container.lastChild);
      }
      for (i=0;i<number;i++) {
          let letI = i;
        let div = document.createElement("div");
        div.name = "answerDiv-" + num + "-" + letI;
        div.className = "answerDiv";
        let p = document.createElement("p");
        p.innerHTML = "Enter Answer Text:";

        let input = document.createElement("input");
        input.type = "text"
        input.name = "answerInput-" + num + "-" + letI;
        input.placeholder = "Enter Answer Text";
        input.setAttribute("required","true");

        let label1 = document.createElement("label");
        let label2 = document.createElement("label");

        label1.innerHTML = "true";
        label2.innerHTML = "false";

        let radio1 = document.createElement("input");
        let radio2 = document.createElement("input")

        radio1.type = "radio";
        radio2.type = "radio";

        radio1.name = "answerRadio-" + num + "-" + letI;
        radio2.name = "answerRadio-" + num + "-" + letI;

        radio1.value = "Y";
        radio2.value = "N";

        radio2.checked = true;

        label1.appendChild(radio1);
        label2.appendChild(radio2);




        div.appendChild(p);
        div.appendChild(input);
        div.appendChild(label1);
        div.appendChild(label2);
        container.appendChild(div);
      }


    }

    function addQuestionFields(){
      let number = document.getElementById("questionNumber").value;
      let container = document.getElementById("questionContainer");
      while (container.hasChildNodes()) {
        container.removeChild(container.lastChild);
      }
      for (i=0;i<number;i++){
        let num = i;
        let div = document.createElement("div");
        div.appendChild(document.createTextNode("Question: " + (num + 1)));
        div.appendChild(document.createElement("br"));
        div.name = "question" + num
        div.className = "questionDiv";

        let label1 = document.createElement("label");
        let label2 = document.createElement("label");
        let label3 = document.createElement("label");
        let label4 = document.createElement("label");

        label1.innerHTML = "QUESTION_RESPONSE";
        label2.innerHTML = "FILL_IN_THE_BLANK";
        label3.innerHTML = "MULTIPLE_CHOICE";
        label4.innerHTML = "PICTURE_RESPONSE";

        let radio1 = document.createElement("input");
        let radio2 = document.createElement("input");
        let radio3 = document.createElement("input");
        let radio4 = document.createElement("input");

        radio1.type = "radio";
        radio2.type = "radio";
        radio3.type = "radio";
        radio4.type = "radio";

        radio1.onclick =  function() {removeAdditional(num)}
        radio2.onclick =  function() {fillBlankText(num)}
        radio3.onclick =  function() {removeAdditional(num)}
        radio4.onclick =  function() {pictureResponse(num)}

        radio1.name = "question-radio-" + num;
        radio2.name = "question-radio-" + num;
        radio3.name = "question-radio-" + num;
        radio4.name = "question-radio-" + num;

        radio1.checked = true;

        radio1.value = "QUESTION_RESPONSE";
        radio2.value = "FILL_IN_THE_BLANK";
        radio3.value = "MULTIPLE_CHOICE";
        radio4.value = "PICTURE_RESPONSE";


        label1.appendChild(radio1);
        label2.appendChild(radio2);
        label3.appendChild(radio3);
        label4.appendChild(radio4);

        div.appendChild(label1);
        div.appendChild(document.createElement("br"));
        div.appendChild(label2);
        div.appendChild(document.createElement("br"));
        div.appendChild(label3);
        div.appendChild(document.createElement("br"));
        div.appendChild(label4);
        div.appendChild(document.createElement("br"));

        let additionalDiv = document.createElement("div");

        additionalDiv.setAttribute("id","additionalInfo-" + num);
        div.appendChild(additionalDiv);
        let name = document.createElement("p");
        name.innerHTML = "Text:";
        div.appendChild(name);

        div.appendChild(document.createElement("br"))

        let textarea = document.createElement("textarea");
        textarea.rows = "4";
        textarea.cols = "30";
        textarea.name = "questionText-" + num;

        div.appendChild(textarea);
        div.appendChild(document.createElement("br"));

        let answerInput = document.createElement("input");
        answerInput.type = "number"
        answerInput.name = "answerNumber" +num;
        answerInput.setAttribute("id","answerNumber" + num);
        answerInput.setAttribute("required","true");
        answerInput.addEventListener("keyup",function() {addAnswerField(num),false});

        answerInput.placeholder = "Enter Answers number";

        // let answerButton = document.createElement("a");
        // answerButton.href = "#/";
        // answerButton.innerHTML = "Fill Answer Details";
        let answerContainer = document.createElement("div");
        answerContainer.setAttribute("id","answerContainer-" + num);
        div.appendChild(document.createElement("br"))
        div.appendChild(document.createTextNode("Answers Number"));
        div.appendChild(document.createElement("br"));
        div.appendChild(answerInput);
        div.appendChild(document.createElement("br"));

        // answerButton.onclick =  function() {addAnswerField(num)}
        // div.appendChild(answerButton);
        div.appendChild(answerContainer)
        container.appendChild(div);
        container.appendChild(document.createElement("br"));

      }
    }
  </script>
</head>
<body>
<jsp:include page="/main/header.jsp"/>
  <h1>Add Quiz</h1>
  <form action = "/CreateQuizServlet" method="post">
    <label>Name: </label>
    <input type="text" name = "name" placeholder="Enter Name" required>
    <br>

    <label>Description: </label>
    <input type="text" name = "description" placeholder="Enter Name" required>
    <br>

    <input onkeyup="addQuestionFields()" type="number" id="questionNumber" name="questionNumber"  placeholder="Enter Question number..." value="" required>Number of questions: <br />
<%--    <a href="#" id="filldetails" onclick="addQuestionFields()">Fill question details</a>--%>
    <div id="questionContainer"></div>

    <input type="submit"> <br>

  </form>

<%--<script><%@include file="/quiz/createQuiz.js"%></script>--%>
</body>
</html>
