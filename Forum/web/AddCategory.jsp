<%-- 
    Document   : AddCategory
    Created on : 31 Oct 2021, 1:15:09 PM
    Author     : Sanele
    Description : This file allows a user to add a new category for topics.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Category</title>
        <%-- This line of code includes a header JSP page --%>
        <jsp:include page="Header.jsp"/>
    </head>
    <body>
        <%-- This line of code includes a header JSP page --%>
        <jsp:include page="Menu.jsp"/>
        <%-- This div block is used to displays results for and errors associated with adding a category --%>
        <div class="formMain" style="color: white">
            <center><div id="exception" class="subtitle" style="color: red">${requestScope.Exception}</div></center>
            <center><div id="response" class="subtitle" style="color: lawngreen">${requestScope.Result}</div></center>
        </div>
        <%
            out.println("<form method='post' action='CategoriesServlet' style='border-style: solid;"
                    + " border-radius: 15px ; margin:10px; padding: 20px; color: white' class='formMain'>");
            out.println("<div class='title'>Category</div>");
            out.println("<div class='subtitle'>Add a Category</div>");
            out.println("<div class='input-container ic1'>");
            out.println("<input name='Category' class='input' required='required' type='text' placeholder=' ' />");
            out.println("<div class='cut'></div>");
            out.println("<label for='Category' class='placeholder'>Category</label></div>");
            out.println("<div class='input-container ic2'>");
            out.println("<input name='Description' class='input' required='required' type='text' placeholder=' ' />");
            out.println("<div class='cut'></div>");
            out.println("<label for='Description' class='placeholder'>Description</label></div>");
            out.println("<button name='Add' type='text' class='submit'>Add Category</button>");
            out.println("</form>");
        %>
        <%-- This line of code includes a footer JSP page --%>
        <jsp:include page="Footer.jsp"/>
    </body>
</html>
