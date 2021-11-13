<%-- 
    Document   : Topics
    Created on : 28 Oct 2021, 4:55:43 PM
    Author     : Sanele
    Description : This file displays topics available under a selected category.
--%>

<%@page import="beans.UserBean"%>
<%@page import="beans.CategoryBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.TopicBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Topics</title>
        <jsp:include page="Header.jsp"/>
        <script>
            function viewComments() {
                var comment = document.getElementById("comment");
                comment.parentNode.removeChild(comment);
                document.getElementById("topicComment").submit();
            }
        </script>
    </head>
    <body>
        <jsp:include page="Menu.jsp"/>
        <div clss="formMain" style="color: white">
            <center class="subtitle" style="color: red">${requestScope.Exception}</center>
            <center class="subtitle" style="color: lawngreen">${requestScope.Result}</center>
        </div>
        <%
            ArrayList<TopicBean> lstTopic = (ArrayList<TopicBean>)request.getSession().getAttribute("Topics");
            if(lstTopic.isEmpty()){
                out.println("<form method='post' action='CategoriesServlet' style='border-style: solid;"
                        + " border-radius: 15px ; margin:10px; padding: 20px; color: white' class='formMain'>");
                out.println("<div class='subtitle'>There are no topics under this category</div>");
                out.println("<button name='Back' type='text' class='submit'>Go To Categories</button>");
                out.println("</form>");
            } else {
                ArrayList<CategoryBean> lstCategory = (ArrayList<CategoryBean>)request.getSession().getAttribute("TopicCat");
                ArrayList<UserBean> lstUsers = (ArrayList<UserBean>)request.getSession().getAttribute("Users");
                UserBean user = (UserBean)request.getSession().getAttribute("login");
                
                for(int i = 0; i < lstTopic.size(); i++) {
                    out.println("<form method='post' action='CommentsServlet' id='topicComment' style='border-style: solid;"
                        + " border-radius: 15px ; margin: 10px; padding: 20px; color: white;' class='formMain'>");
                    out.println("<center><div class='title'>Topic Category: "+lstCategory.get(i).getCategoryName()+"</div></center>");
                    out.println("<div class='user'>"+lstUsers.get(i).getUsername()+"</div>");
                    out.println("<input type='hidden' name='CommentBy' value='"+ user.getId()+"'>");
                    out.println("<div class='standout' style='font-size: small'>Posted On: "+lstTopic.get(i).getTopicDate()+"</div>");
                    out.println("<div class='subtitle'>Topic: "+lstTopic.get(i).getTitle()+"</div>");
                    out.println("<div style='margin: 10px 0px 10px 0px'>"+ lstTopic.get(i).getDescription() +"</div>");
                    out.println("<input type='hidden' name='TopicId' value='"+lstTopic.get(i).getId()+"'>");
                    out.println("<div id='comment'>");
                    out.println("<div class='input-container ic1'>");
                    out.println("<input name='Comment' class='input' required='required' type='text' placeholder=' ' />");
                    out.println("<div class='cut'></div>");
                    out.println("<label for='Comment' class='placeholder'>Comment</label></div>");
                    out.println("<p><button type='text' class='button_slide slide_right item' style='font-size: 10px;'>Comment</button></p>");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("<button name='Comments' type='text' class='submit' onclick='viewComments()'>View Comments</button>");
                    out.println("</form>");
                }
            }
        %>
        <jsp:include page="Footer.jsp"/>
    </body>
</html>
