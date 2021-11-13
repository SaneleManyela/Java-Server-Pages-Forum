<%-- 
    Document   : PasswordRecovery
    Created on : 01 Nov 2021, 11:57:09 PM
    Author     : Sanele
    Description : This file has forms for the user to enter their username,
                  and answer a security question in order to recover a
                  forgotten password.
--%>

<%@page import="beans.UserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Password Recovery</title>
        <%-- This line of code includes a header JSP page --%>
        <jsp:include page="Header.jsp"/>
    </head>
    <body>
        <%-- This line of code includes a header JSP page --%>
        <jsp:include page="Menu.jsp"/>
        <div class="formMain">
            <center class="subtitle" style="color: lawngreen">${requestScope.Result}</center>
            <center class="subtitle" style="color: red">${requestScope.Exception}</center>
        </div>
        <%
            // A UserBean object whose value is set by requesting from the session
            // an attribute with a value a of type UserBean
            UserBean user = (UserBean)request.getSession().getAttribute("Recovery");
            if(user == null){ // Check if the UserBean object is null
                out.println("<form method='post' action='PasswordRecoveryServlet' style='border-style: solid; border-radius: 15px ; margin:10px; padding: 20px;"
                        + " color: white' class='formMain'>");
                out.println("<div class='subtitle'>Enter your username:</div>");
                out.println("<div class='input-container ic1'>");
                out.println("<input name='Username' class='input' required='required' type='text' placeholder=' ' />");
                out.println("<div class='cut'></div>");
                out.println("<label for='Username' class='placeholder'>Username</label></div>");
                out.println("<p><button type='text' class='button_slide slide_right item' style='font-size: 10px;'>Enter</button></p>");
                out.println("</div>");
                out.println("</form>");
            } else {
                // display a form with security question and request an answer. 
                // if answer is valid show password, else relay to the user the outcome
                out.println("<form method='post' action='PasswordRecoveryServlet' style='border-style: solid; border-radius: 15px ; margin:10px; padding: 20px;"
                        + " color: white' class='formMain'>");
                out.println("<div class='subtitle'>To recover your password answer this question:</div>");
                out.println("<p>"+user.getSecurityQuestion()+"</p>");
                out.println("<div class='input-container ic1'>");
                out.println("<input name='PasswordSec' class='input' required='required' type='text' placeholder=' ' />");
                out.println("<div class='cut'></div>");
                out.println("<label for='PasswordSec' class='placeholder'>Answer</label></div>");
                out.println("<p><button type='text' class='button_slide slide_right item' style='font-size: 10px;'>Enter</button></p>");
                out.println("</div>");
                out.println("</form>");
            }
        %>
        <%-- This line of code includes a footer JSP page --%>
        <jsp:include page="Footer.jsp"/>
    </body>
</html>
