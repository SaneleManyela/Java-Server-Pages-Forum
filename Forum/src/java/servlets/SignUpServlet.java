/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.DatabaseBean;
import beans.UserBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sanele
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {

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
        UserBean user = new UserBean();
        try{
            user.setFirstName(request.getParameter("Firstname"));
            user.setLastName(request.getParameter("Lastname"));
            user.setEmail(request.getParameter("Email"));
            user.setUsername(request.getParameter("Username"));
            user.setPassword(request.getParameter("Password"));
            user.setSecurityQuestion(request.getParameter("PasswordQuestion"));
            user.setSecurityAnswer(request.getParameter("PasswordSec"));
            
            DatabaseBean database = new DatabaseBean();
            
            if(database.checkData("SELECT Username FROM Users WHERE Username ='" 
                    + user.getUsername() + "'")){
                request.setAttribute("Exception", "Username exists");
                request.getRequestDispatcher("SignUp.jsp").forward(request, response);
                
            } else if(database.executeQueries("INSERT INTO Users("
                    + "Firstname, Lastname, Email, Username, Password, "
                    + "SecurityQuestion, SecurityAnswer)"
                    + "VALUES('"+ user.getFirstName() +"', '"+ user.getLastName()
                    + "', '"+ user.getEmail() +"', '"+ user.getUsername() +"','"
                    + user.getPassword() +"','"+user.getSecurityQuestion()+"','"
                            +user.getSecurityAnswer()+"')")){
                request.setAttribute("Result", "Account has been created");
                request.getRequestDispatcher("SignUp.jsp").forward(request, response);
            }
        } catch(Exception e){
            request.setAttribute("Exception", e.getMessage());
            request.getRequestDispatcher("SignUp.jsp").forward(request, response);
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
