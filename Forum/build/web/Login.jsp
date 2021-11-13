<%-- 
    Document   : Login
    Created on : 28 Oct 2021, 9:45:43 AM
    Author     : Sanele
    Description : This file logs in a user to the application, 
                  it requires a username and a password. It also
                  allows a user an option to recover a forgotten
                  password.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <%-- This line of code includes a header JSP page --%>
        <jsp:include page="Header.jsp"/>
    </head>
    <body>
        <%-- This line of code includes a menu JSP page --%>
        <jsp:include page="Menu.jsp"/>
        <%-- The following div element shows login errors --%>
        <div class="formMain">
            <center class="subtitle" style="color: red">${requestScope.Exception}</center>
        </div>
        <form method="post" action="LoginServlet" class="formMain">
            <div class="form">
                <div class="title">Welcome</div>
                <div class="subtitle">Login</div>
                <div class="input-container ic1">
                    <input name="Username" id="username" class="input" required='required' type="text" placeholder=" " />
                    <div class="cut"></div>
                    <label for="Username" class="placeholder">Username</label>
                </div>
                <div class="input-container ic2">
                    <input name="Password" class="input" required='required' type="password" placeholder=" " />
                    <div class="cut"></div>
                    <label for="password" class="placeholder">Password</label>
                </div>
                <center><p><a href="PasswordRecovery.jsp" class="subtitle">forgot password?</a></p></center>
                <button name="login" type="text" class="submit" style="margin-top: 15px">login</button>
            </div>
        </form>
        <%-- This line of code includes a footer JSP page --%>
        <jsp:include page="Footer.jsp"/>
    </body>
</html>
