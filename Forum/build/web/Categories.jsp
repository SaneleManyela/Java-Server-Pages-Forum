<%-- 
    Document   : Categories
    Created on : 28 Oct 2021, 4:54:32 PM
    Author     : Sanele
    Description : This file displays available categories and
                  provide a link to view topic under each category 
--%>

<%@page import="beans.CategoryBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categories</title>
        <%-- This line of code includes a header JSP page --%>
        <jsp:include page="Header.jsp"/>
    </head>
    <body>
        <%-- This line of code includes a menu JSP page --%>
        <jsp:include page="Menu.jsp"/>
        
        <%
            // An ArrayList object that stores objects of CategoryBean is retrieved from the current session
            ArrayList<CategoryBean> lstCategories = (ArrayList<CategoryBean>)request.
                getSession().getAttribute("Categories");
            for(CategoryBean cat : lstCategories){ 
                out.println("<form method='post' action='TopicsServlet' style='border-style: solid;"
                        + " border-radius: 15px ; margin:10px; padding: 20px; color: white' class='formMain'>");
                out.println("<div class='subtitle'>"+cat.getCategoryName()+"</div>");
                out.println("<p>"+cat.getDescription()+"</p>");
                out.println("<input type='hidden' name='CategoryId' value='"+cat.getID()+"'>");
                out.println("<button name='Category' type='text' class='submit'>View Category Topics</button>");
                out.println("</form>");
            }
        %>
        <%-- This line of code includes a footer JSP page --%>
        <jsp:include page="Footer.jsp"/>
    </body>
</html>
