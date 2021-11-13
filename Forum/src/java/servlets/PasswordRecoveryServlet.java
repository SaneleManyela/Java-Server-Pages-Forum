/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.DatabaseBean;
import beans.UserBean;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
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
@WebServlet(name = "PasswordRecoveryServlet", urlPatterns = {"/PasswordRecoveryServlet"})
public class PasswordRecoveryServlet extends HttpServlet {

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
        
        DatabaseBean database = new DatabaseBean();
        HttpSession session = request.getSession();
        
        if(request.getParameter("Username") != null){
            UserBean user = getFromDatabase(new UserBean(), request.getParameter("Username"), database);
            session.setAttribute("Recovery", user);
            response.sendRedirect("PasswordRecovery.jsp");
            
        } else if(request.getParameter("PasswordSec") != null){
            UserBean user;
            user = (UserBean)request.getSession().getAttribute("Recovery");
            if(user.getSecurityAnswer().equals(request.getParameter("PasswordSec"))) {
                request.setAttribute("Result", "Your password: " + user.getPassword());
                request.getRequestDispatcher("PasswordRecovery.jsp").forward(request, response);
            } else {
                request.setAttribute("Exception", "Wrong answer to the security question");
                request.getRequestDispatcher("PasswordRecovery.jsp").forward(request, response);
            }
        }
    }
    
    private UserBean getFromDatabase(UserBean user, String username, DatabaseBean database) {
        
        try(Statement stStatement = database.databaseConnection().createStatement();
                ResultSet rs = stStatement.executeQuery(
                    "SELECT Password, SecurityQuestion, SecurityAnswer FROM Users WHERE Username ='"+username+"'")){
                        
            while(rs.next()){
                user = new UserBean(rs.getString("Password"), rs.getString("SecurityQuestion"), 
                        rs.getString("SecurityAnswer"));
            }
            
            stStatement.close();
            rs.close();
        } catch(Exception ex){
        }
        return user;
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
