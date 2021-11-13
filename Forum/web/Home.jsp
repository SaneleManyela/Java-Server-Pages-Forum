<%-- 
    Document   : Home
    Created on : 28 Oct 2021, 11:00:40 AM
    Author     : Sanele
    Description: This is the application's landing page,
                 it has a menu that navigates to other pages.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" session="true">
        <title>Home</title></head>
        <%-- This line of code includes a header JSP page --%>
        <jsp:include page="Header.jsp"/>
    <body>
        <%-- This line of code includes a menu JSP page --%>
        <jsp:include page="Menu.jsp"/>
        <div class="formMain">
            <div class="form">
                <center class="title"><b>Developer Konner</b></center>
            </div>
        </div>
        <%-- This line of code includes a footer JSP page --%>
        <jsp:include page="Footer.jsp"/>
    </body>
</html>
