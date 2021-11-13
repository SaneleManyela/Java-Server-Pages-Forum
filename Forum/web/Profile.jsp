<%-- 
    Document   : Profile
    Created on : 31 Oct 2021, 11:22:17 PM
    Author     : Sanele
    Description : This file allows a user to update their profile details.
--%>

<%@page import="beans.UserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Update</title>
        <%-- This line of code includes a header JSP page --%>
        <jsp:include page="Header.jsp"/>
    </head>
    <body>
        <%-- This line of code includes a menu JSP page --%>
        <jsp:include page="Menu.jsp"/>
        
        <% 
            // A UserBean object whose value is set by requesting from the session
            // an attribute with a value a of type UserBean
            UserBean user = (UserBean)request.getSession().getAttribute("Profile");
        %>
        <div class="formMain">
            <center class="subtitle" style="color: lawngreen">${requestScope.Result}</center>
            <center class="subtitle" style="color: red">${requestScope.Exception}</center>
        </div>
        <form method="post" action="ProfilesServlet" class="formMain">
            <div class="form">
                <div class="title">Update</div>
                <div class="subtitle">Let's update your profile!</div>
                <div class="input-container ic1">
                    <input name="Firstname" value="<%= user.getFirstName() %>" class="input" required='required' type="text" placeholder=" " />
                    <div class="cut"></div>
                    <label for="Firstname" class="placeholder">Firstname</label>
                </div>
                <div class="input-container ic2">
                    <input name="Lastname" value="<%= user.getLastName() %>" class="input" required='required' type="text" placeholder=" " />
                    <div class="cut"></div>
                    <label for="Lastname" class="placeholder">Lastname</label>
                </div>
                <div class="input-container ic2">
                    <input name="Email" value="<%= user.getEmail() %>" class="input" required='required' type="text" placeholder=" " />
                    <div class="cut"></div>
                    <label for="Email" class="placeholder">Email</label>
                </div>
                <div class="input-container ic2">
                    <input name="Username" value="<%= user.getUsername() %>" class="input" required='required' type="text" placeholder=" " />
                    <div class="cut"></div>
                    <label for="Username" class="placeholder">Username</label>
                </div>
                <div class="input-container ic2">
                    <input name="Password" value="<%= user.getPassword() %>" class="input" required='required' type="text" placeholder=" " />
                    <div class="cut"></div>
                    <label for="password" class="placeholder">Password</label>
                </div>
                <div class="input-container ic2">
                    <select name='PasswordQuestion' class='input' placeholder=' '>
                        <option value="favorite technology & memorable date & favorite word"
                                <% if(user.getSecurityQuestion().equals("favorite technology & memorable date & favorite word"))
                                {out.println("selected"); }%> >
                            Favorite technology & memorable date & favorite word
                        </option>
                        <option value="World cup memory & high school memory & favorite number" 
                                <% if(user.getSecurityQuestion().equals("World cup memory & high school memory & favorite number"))
                                {out.println("selected"); }%> >
                            World cup memory & high school memory & favorite number
                        </option>
                        <option value="Favorite constellation & star system & exoplanet & moon"
                                <% if(user.getSecurityQuestion().equals("Favorite constellation & star system & exoplanet & moon"))
                                {out.println("selected"); }%> >
                            Favorite constellation & star system & exoplanet & moon
                        </option>
                    </select>
                    <div class='cut'></div>
                    <label for='PasswordQuestion' class='placeholder'>Question</label>
                </div>
                <div class="input-container ic2">
                    <input name="PasswordSec" value="<%= user.getSecurityAnswer() %>" class="input" required='required' type="text" placeholder=" " />
                    <div class="cut"></div>
                    <label for="PasswordSec" class="placeholder">Security Answer</label>
                </div>
                <button name="Update" type="text" class="submit">Update</button>
            </div>
        </form>
        <%-- This line of code includes a footer JSP page --%>
        <jsp:include page="Footer.jsp"/>
    </body>
</html>
