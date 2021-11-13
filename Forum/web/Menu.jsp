<%-- 
    Document   : Menu
    Created on : 28 Oct 2021, 9:44:53 AM
    Author     : Sanele
    Description : This file defines a navigation menu that a user can use to 
                  navigate to other pages. Each menu is displayed om a condition
                  that a user is logged in or not.
--%>

<%@page import="beans.UserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <div class="formMain list">
        <div id="wrapper">
            <div id="menu" class="list">
                <ul>
                    <% if(request.getSession().getAttribute("isLoggedIn").toString().equals("true")){ %>
                        <li><a class="item button_slide slide_right" href="Home.jsp">Home</a></li>
                        <li><a class="item button_slide slide_right" href="CategoriesServlet">Categories & Topics</a></li>
                        <li><a class="item button_slide slide_right" href="CategoriesServlet?Post=post">Post</a></li>
                        <li><a class="item button_slide slide_right" href="ProfilesServlet">Update Profile</a></li>
                        <li><a class="item button_slide slide_right" href="LogoutServlet">Logout</a></li>
                    <% } else { %>
                        <li><a class="item button_slide slide_right" href="Home.jsp">Home</a></li>
                        <li><a class="item button_slide slide_right" href="Login.jsp">Login</a></li>
                        <li><a class="item button_slide slide_right" href="SignUp.jsp">Sign-up</a></li>
                    <%}%>
                </ul>
            </div>
        </div>
    </div>
</html>
