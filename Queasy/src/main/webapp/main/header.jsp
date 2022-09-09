<style>

</style>

<jsp:include page="/css/main.jsp"/>

<div class="topnav">
    <a href="/welcome">Home</a>
    <a href="/mails">Mails</a>
    <a href="/quizzes">Quizzes</a>

    <div style="float: left; padding-left: 16px; padding-right: 16px;">
    <div class = "search-container">
        <form action = "/UserSearchServlet" method = "post">
            <input type = "text" placeholder="Username Search..." name = "search">
            <input type = "submit">
        </form>
    </div>
    <div class = "search-container">
    <form class = "search-container" action = "/QuizSearchServlet">
        <input type = "text" placeholder="Quiz Search..." name = "search">
        <input type = "submit">
    </form>
    </div>
    </div>

    <div class="topnav-right">
        <a href="/user/userDetails.jsp">Profile</a>
        <a href="/LogoutServlet"><img src = "https://cdn-icons-png.flaticon.com/512/1243/1243950.png" alt = "Logout"></a>
    </div>
</div>