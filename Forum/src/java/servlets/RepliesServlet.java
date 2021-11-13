/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.CommentBean;
import beans.DatabaseBean;
import beans.ReplyBean;
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
@WebServlet(name = "RepliesServlet", urlPatterns = {"/RepliesServlet"})
public class RepliesServlet extends HttpServlet {

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
        ReplyBean reply = new ReplyBean();
        DatabaseBean database = new DatabaseBean();
        HttpSession session;
        ArrayList<ReplyBean> lstReplies = new ArrayList<>();
        
        ArrayList<UserBean> lstUsers = new ArrayList<>();
        
        if(request.getParameter("Reply") == null){
            reply.setComment(Integer.parseInt(request.getParameter("Comment")));
            
            if(database.checkData("SELECT * FROM Replies WHERE Comment ="+ reply.getComment())){
                getFromDatabase(reply, database, lstUsers, lstReplies);
                CommentBean comment = new CommentBean(database.getField("SELECT Comment FROM Comments WHERE ID ="+reply.getComment()));
                session = request.getSession();
                session.setAttribute("Comment", comment);
                session.setAttribute("Replies", lstReplies);
                session.setAttribute("Repliers", lstUsers);
                response.sendRedirect("Replies.jsp");
            } else {
                session = request.getSession();
                session.setAttribute("Replies", lstReplies);
                response.sendRedirect("Replies.jsp");
            }
        } else {
            try{
                reply.setComment(Integer.parseInt(request.getParameter("Comment")));
                reply.setReply(request.getParameter("Reply"));
                reply.setReplyBy(Integer.parseInt(request.getParameter("ReplyBy")));
                reply.setReplyDate(database.getField("SELECT CURRENT_TIMESTAMP"));
                
                if(database.executeQueries("INSERT INTO Replies(Comment, Reply, ReplyBy, ReplyDate)"
                        + "VALUES('"+reply.getComment()+"','"+reply.getReply()
                        +"','"+reply.getReplyBy()+"','"+reply.getReplyDate()+"')")){
                    request.setAttribute("Result", "Reply has been posted");
                    request.getRequestDispatcher("Comments.jsp").forward(request, response);
                }
            } catch(Exception ex){
                request.setAttribute("Exception", ex.getMessage());
                request.getRequestDispatcher("Comments.jsp").forward(request, response);
            }
        }
        
    }
    
    private void getFromDatabase(ReplyBean replyComment, DatabaseBean database, ArrayList<UserBean> lstUsers,
            ArrayList<ReplyBean> lstReplies) {
        
        try(Statement stStatement = database.databaseConnection().createStatement();
                ResultSet rs = stStatement.executeQuery(
                    "SELECT Comment, Reply, ReplyBy, ReplyDate FROM Replies WHERE Comment =" + replyComment.getComment())){
                        
            while(rs.next()){
                lstReplies.add(new ReplyBean(rs.getInt("Comment"), rs.getInt("ReplyBy"), 
                        rs.getString("Reply"), rs.getString("ReplyDate"))); 
            }
            
            stStatement.close();
            rs.close();
            
            for(ReplyBean reply : lstReplies){
                lstUsers.add(new UserBean(database.getField("SELECT Username FROM Users WHERE ID =" + reply.getReplyBy())));
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
