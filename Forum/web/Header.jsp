<%-- 
    Document   : Header
    Created on : 28 Oct 2021, 9:43:57 AM
    Author     : Sanele
    Description: This file displays a the name of the forum application
                 and the current user. This file is used throughout the forum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles.css" type="text/css"/>
    </head>
    <div class="formMain">
        <div id="logo">Developer Konner</div>
        <%! String username; %>
        <%  if(!request.getSession().isNew()) {
                beans.UserBean bean = (beans.UserBean)request.
                        getSession().getAttribute("login");
                username = bean != null ? bean.getUsername() : "Guest";
            } else {
                username = "Guest";
                request.getSession().setAttribute("isLoggedIn", false);
            }
            out.println("<div class='username'>Logged in as:"+ username +"</div>");
        %>
    </div> 
</html>
