/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.CommentBean;
import beans.DatabaseBean;
import beans.TopicBean;
import beans.UserBean;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sanele
 */
@WebServlet(name = "CommentsServlet", urlPatterns = {"/CommentsServlet"})
public class CommentsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        CommentBean comment = new CommentBean();
        DatabaseBean database = new DatabaseBean();
        HttpSession session;
        ArrayList<UserBean> lstUsers = new ArrayList<>();
        ArrayList<CommentBean> lstComments = new ArrayList<>();
        ArrayList<TopicBean> lstTopics = new ArrayList<>();
        
        if(request.getParameter("Comment") == null){
            comment.setTopic(Integer.parseInt(request.getParameter("TopicId")));
            
            if(database.checkData("SELECT * FROM Comments WHERE Topic =" + comment.getTopic())) {
                getFromDatabase(comment, database, lstUsers, lstComments, lstTopics);
                session = request.getSession();
                session.setAttribute("Comments", lstComments);
                session.setAttribute("Commenters", lstUsers);
                session.setAttribute("TopicComments", lstTopics);
                response.sendRedirect("Comments.jsp");
            } else {
                session = request.getSession();
                session.setAttribute("Comments", lstComments);
                response.sendRedirect("Comments.jsp");
            }
        } else {
            try{
                comment.setTopic(Integer.parseInt(request.getParameter("TopicId")));
                comment.setComment(request.getParameter("Comment"));
                comment.setCommentBy(Integer.parseInt(request.getParameter("CommentBy")));
                comment.setCommentDate(database.getField("SELECT CURRENT_TIMESTAMP"));
                
                if(database.executeQueries("INSERT INTO Comments(Topic, Comment, CommentBy, CommentDate)"
                        + "VALUES('"+comment.getTopic()+"','"+comment.getComment()+"','"+
                        comment.getCommentBy()+"','"+comment.getCommentDate()+"')")){
                    request.setAttribute("Result", "Comment has been posted");
                    request.getRequestDispatcher("Topics.jsp").forward(request, response);
                }
            } catch(Exception ex) {
                request.setAttribute("Exception", ex.getMessage());
                request.getRequestDispatcher("Topics.jsp").forward(request, response);
            }
        }
    }
    
    private void getFromDatabase(CommentBean commentTopic, DatabaseBean database, ArrayList<UserBean> lstUsers,
            ArrayList<CommentBean> lstComments, ArrayList<TopicBean> lstTopics) {
        
        try(Statement stStatement = database.databaseConnection().createStatement();
                ResultSet rs = stStatement.executeQuery(
                    "SELECT ID, Topic, Comment, CommentBy, CommentDate FROM Comments WHERE Topic =" + commentTopic.getTopic())){
                        
            while(rs.next()){
                lstComments.add(new CommentBean(rs.getInt("ID"), rs.getInt("Topic"), 
                        rs.getString("Comment"), rs.getInt("CommentBy"), rs.getString("CommentDate"))); 
            }
            
            stStatement.close();
            rs.close();
            
            for(CommentBean comment : lstComments){
                lstUsers.add(new UserBean(database.getField("SELECT Username FROM Users WHERE ID =" + comment.getCommentBy())));
                lstTopics.add(new TopicBean(database.getField("SELECT Title FROM Topics WHERE ID =" + comment.getTopic())));
            }
        } catch(Exception ex){
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
