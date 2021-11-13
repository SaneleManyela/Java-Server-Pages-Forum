<%-- 
    Document   : Comments
    Created on : 28 Oct 2021, 4:57:02 PM
    Author     : Sanele
    Description : This file displays comments under a topic and provide
                  an option to reply and view all replies under a comment.
--%>

<%@page import="beans.TopicBean"%>
<%@page import="beans.UserBean"%>
<%@page import="beans.CommentBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comments</title>
        <%-- This line of code includes a header JSP page --%>
        <jsp:include page="Header.jsp"/>
        <%-- This script removes the input field used to reply, making it possible
            to post the form used to display a comment.--%>
        <script>
            function viewReplies() {
                var comment = document.getElementById("reply");
                comment.parentNode.removeChild(comment);
                document.getElementById("commentReply").submit();
            }
        </script>
    </head>
    <body>
        <%-- This line of code includes a menu JSP page --%>
        <jsp:include page="Menu.jsp"/>
        <%-- This div block is used to displays results for and errors associated with adding a comment --%>
        <div class="formMain" style="color: white">
            <center class="subtitle" style="color: red">${requestScope.Exception}</center>
            <center class="subtitle" style="color: lawngreen">${requestScope.Result}</center>
        </div>
        <%
            // An ArrayList object that stores objects of type CommentBean is retrieved from the current session
            ArrayList<CommentBean> lstComments = (ArrayList<CommentBean>)request.getSession().getAttribute("Comments");
            
            if(lstComments.isEmpty()){ // Check if the comments' list has values
                out.println("<form style='border-style: solid; border-radius: 15px ; margin:10px; padding: 20px;"
                        + " color: white' class='formMain'>");
                out.println("<div class='subtitle'>There are no comments under this topic</div>");
                out.println("</form>");
            } else { 
                // An ArrayList object that stores objects of type UserBean is retrieved from the current session
                ArrayList<UserBean> lstUsers = (ArrayList<UserBean>)request.getSession().getAttribute("Commenters");
                
                // An ArrayList object that stores objects of type TopicBean is retrieved from the current session
                ArrayList<TopicBean> lstTopics = (ArrayList<TopicBean>)request.getSession().getAttribute("TopicComments");
                
                // An ArrayList object that stores objects of type UserBean is retrieved from the current session
                UserBean user = (UserBean)request.getSession().getAttribute("login");
                
                // The following loop structure iterates and traverses the ArrayList object set above and
                // display their values on forms.
                for(int i = 0; i < lstComments.size(); i++){
                    out.println("<form id='commentReply' method='post' action='RepliesServlet' style='border-style: solid;"
                        + " border-radius: 15px ; margin:10px; padding: 20px; color: white' class='formMain'>");
                    out.println("<center><div class='title'>Topic: "+lstTopics.get(i).getTitle()+"</div></center>");
                    out.println("<div class='user'>"+lstUsers.get(i).getUsername()+"</div>");
                    out.println("<input type='hidden' name='ReplyBy' value='"+ user.getId()+"'>");
                    out.println("<div class='standout' style='font-size: small'>Posted On: "+lstComments.get(i).getCommentDate()+"</div>");
                    out.println("<div class='subtitle'>Comment: "+lstComments.get(i).getComment()+"</div>");
                    out.println("<input type='hidden' name='Comment' value='"+lstComments.get(i).getId()+"'>");
                    out.println("<div id='reply'>");
                    out.println("<div class='input-container ic1'>");
                    out.println("<input name='Reply' class='input' required='required' type='text' placeholder=' ' />");
                    out.println("<div class='cut'></div>");
                    out.println("<label for='Reply' class='placeholder'>Reply</label></div>");
                    out.println("<p><button type='text' class='button_slide slide_right item' style='font-size: 10px;'>Reply</button></p>");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("<button name='Replies' type='text' class='submit' onclick='viewReplies()'>View Replies</button>");
                    out.println("</form>");
                }
            }
        %>
        <%-- This line of code includes a footer JSP page --%>
        <jsp:include page="Footer.jsp"/>
    </body>
</html>
