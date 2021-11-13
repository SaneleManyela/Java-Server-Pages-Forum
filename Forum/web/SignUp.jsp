<%-- 
    Document   : SignUp
    Created on : 28 Oct 2021, 9:45:32 AM
    Author     : Sanele
    Description : This file and its form allow a user to register a user profile.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign-Up</title>
        <jsp:include page="Header.jsp"/>
    </head>
    <body>
        <jsp:include page="Menu.jsp"/>
        <div class="formMain">
            <center class="subtitle" style="color: lawngreen">${requestScope.Result}</center>
            <center class="subtitle" style="color: red">${requestScope.Exception}</center>
        </div>
        <form method="post" action="SignUpServlet" class="formMain">
            <div class="form">
                <div class="title">Welcome</div>
                <div class="subtitle">Let's create your profile!</div>
                <div class="input-container ic1">
                    <input name="Firstname" class="input" required='required' type="text" placeholder=" " />
                    <div class="cut"></div>
                    <label for="Firstname" class="placeholder">Firstname</label>
                </div>
                <div class="input-container ic2">
                    <input name="Lastname" class="input" required='required' type="text" placeholder=" " />
                    <div class="cut"></div>
                    <label for="Lastname" class="placeholder">Lastname</label>
                </div>
                <div class="input-container ic2">
                    <input name="Email" class="input" required='required' type="text" placeholder=" " />
                    <div class="cut"></div>
                    <label for="Email" class="placeholder">Email</label>
                </div>
                <div class="input-container ic2">
                    <input name="Username" class="input" required='required' type="text" placeholder=" " />
                    <div class="cut"></div>
                    <label for="Username" class="placeholder">Username</label>
                </div>
                <div class="input-container ic2">
                    <input name="Password" class="input" required='required' type="text" placeholder=" " />
                    <div class="cut"></div>
                    <label for="password" class="placeholder">Password</label>
                </div>
                <div class="input-container ic2">
                    <select name='PasswordQuestion' class='input' placeholder=' '>
                        <option value="favorite technology & memorable date & favorite word">
                            Favorite technology & memorable date & favorite word
                        </option>
                        <option value="World cup memory & high school memory & favorite number">
                            World cup memory & high school memory & favorite number
                        </option>
                        <option value="Favorite constellation & star system & exoplanet & moon">
                            Favorite constellation & star system & exoplanet & moon
                        </option>
                    </select>
                    <div class='cut'></div>
                    <label for='PasswordQuestion' class='placeholder'>Question</label>
                </div>
                <div class="input-container ic2">
                    <input name="PasswordSec" class="input" required='required' type="text" placeholder=" " />
                    <div class="cut"></div>
                    <label for="PasswordSec" class="placeholder">Security Answer</label>
                </div>
                <button name="signup" type="text" class="submit">signup</button>
            </div>
        </form>
        <jsp:include page="Footer.jsp"/>
    </body>
</html>
