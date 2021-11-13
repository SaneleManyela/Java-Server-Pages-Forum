<%-- 
    Document   : Replies
    Created on : 01 Nov 2021, 5:42:48 PM
    Author     : Sanele
    Description : This file displays replies of a comment, the comment which the replies are under,
                  content of the reply, including who replied, and when.
--%>

<%@page import="beans.CommentBean"%>
<%@page import="beans.UserBean"%>
<%@page import="beans.ReplyBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Replies Page</title>
        <%-- This line of code includes a header JSP page --%>
        <jsp:include page="Header.jsp"/>
    </head>
    <body>
        <%-- This line of code includes a menu JSP page --%>
        <jsp:include page="Menu.jsp"/>
        <%
            // An ArrayList data structure is used to display replies  
            ArrayList<ReplyBean> lstReplies = (ArrayList<ReplyBean>)request.getSession().getAttribute("Replies");
            if(lstReplies.isEmpty()){
                out.println("<form style='border-style: solid; border-radius: 15px ; margin:10px; padding: 20px;"
                        + " color: white' class='formMain'>");
                out.println("<div class='subtitle'>There are no replies under this comment</div>");
                out.println("</form>");
            } else {
                ArrayList<UserBean> lstRepliers = (ArrayList<UserBean>)request.getSession().getAttribute("Repliers");
                CommentBean comment = (CommentBean)request.getSession().getAttribute("Comment");
                
                out.println("<form id='commentReply' method='post' action='CategoriesServlet' style='border-style: solid;"
                        + " border-radius: 15px ; margin:10px; padding: 20px; color: white' class='formMain'>"); 
                out.println("<div class='title'>Comment: "+comment.getComment()+"</div>");
                
                for(int j = 0; j < lstReplies.size(); j++){
                    out.println("<div class='user'>"+lstRepliers.get(j).getUsername()+"</div>");
                    out.println("<div class='standout' style='font-size: small'>Posted On: "+lstReplies.get(j).getReplyDate()+"</div>");
                    out.println("<div class='subtitle'>Reply: "+lstReplies.get(j).getReply()+"</div>");
                }
                out.println("<button id='view' type='text' class='submit'>Go Top (Categories)</button>");
                out.println("</form>");
            }
        %>
        <%-- This line of code includes a footer JSP page --%>
        <jsp:include page="Footer.jsp"/>
    </body>
</html>
