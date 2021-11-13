<%-- 
    Document   : NewPost
    Created on : 29 Oct 2021, 10:17:58 PM
    Author     : Sanele
    Description : This file allows users to post new topics, it also includes
                  an option to add a new category should the categories available 
                  not include the required category.
--%>

<%@page import="beans.UserBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.CategoryBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post New Topic</title>
        <%-- This line of code includes a header JSP page --%>
        <jsp:include page="Header.jsp"/>
        <%-- This script is used to redirect the application to the AddCategory JSP. --%>
        <script>
            function display() {
                window.location="AddCategory.jsp";
            }
        </script>
    </head>
    <body>
        <%-- This line of code includes a header JSP page --%>
        <jsp:include page="Menu.jsp"/>
        <%-- This div block is used to displays results for and errors associated with adding a new post --%>
        <div class="formMain" style="color: white">
            <center class="subtitle" style="color: red">${requestScope.Exception}</center>
            <center class="subtitle" style="color: lawngreen">${requestScope.Result}</center>
        </div>
        <%
            out.println("<form method='post' action='TopicsServlet' style='border-style: solid;"
                    + " border-radius: 15px ; margin:10px; padding: 20px; color: white' class='formMain'>");
            out.println("<div class='title'>Post</div>");
            out.println("<div class='subtitle'>Post a Topic</div>");
            
            out.println("<div class='input-container ic1'>");
            out.println("<input name='Topic' class='input' required='required' type='text' placeholder=' ' />");
            out.println("<div class='cut'></div>");
            out.println("<label for='Topic' class='placeholder'>Topic</label>");
            out.println("</div>");
            
            out.println("<div class='input-container ic2'>");
            out.println("<input name='TopicDescription' class='input' required='required' type='text' placeholder=' ' />");
            out.println("<div class='cut'></div>");
            out.println("<label for='TopicDescription' class='placeholder'>Description</label>");
            out.println("</div>");
            
            // An ArrayList object that stores objects of CategoryBean is retrieved from the current session
            ArrayList<CategoryBean> lstCategory = (ArrayList<CategoryBean>)request.getSession().getAttribute("Categories");
            out.println("<div class='input-container ic2'>");
            out.println("<select name='TopicCategory' class='input' placeholder=' '>");
            for(int i = 0; i < lstCategory.size(); i++){
                out.println("<option value='"+lstCategory.get(i).getID()+"'>"+lstCategory.get(i).getCategoryName()+"</option>");
            }
            out.println("</select>");
            out.println("<div class='cut'></div>");
            out.println("<label for='TopicCategory' class='placeholder'>Category</label>");
            out.println("</div>");
            out.println("<button id='displayer' type='button' class='button_slide slide_right item' style='font-size: 10px; margin: 10px 0px 10px 0px' onclick='display()'>Add Category</button>");
            
            // A UserBean object whose value is set by requesting from the session
            // an attribute with a value a of type UserBean
            UserBean user = (UserBean)request.getSession().getAttribute("login");
            out.println("<input type='hidden' name='PostedBy' value='"+user.getId()+"'>");
            out.println("<button name='Post' type='text' class='submit'>Post</button>");
            out.println("</form>");
        %>
        <%-- This line of code includes a footer JSP page --%>
        <jsp:include page="Footer.jsp"/>
    </body>
</html>
