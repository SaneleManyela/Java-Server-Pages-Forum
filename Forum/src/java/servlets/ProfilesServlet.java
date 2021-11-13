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
@WebServlet(name = "ProfilesServlet", urlPatterns = {"/ProfilesServlet"})
public class ProfilesServlet extends HttpServlet {

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
        UserBean user;
        
        DatabaseBean database = new DatabaseBean();
        HttpSession session = request.getSession();
        UserBean login = (UserBean)session.getAttribute("login");
        
        if(request.getParameter("Firstname") == null){
            user = getFromDatabase(new UserBean(), login, database);
            session.setAttribute("Profile", user);
            response.sendRedirect("Profile.jsp");
        } else {
            try{
                user = new UserBean();
                UserBean profile = (UserBean)request.getSession().getAttribute("Profile");
                user.setId(profile.getId());
                user.setFirstName(request.getParameter("Firstname"));
                user.setLastName(request.getParameter("Lastname"));
                user.setEmail(request.getParameter("Email"));
                user.setPassword(request.getParameter("Password"));
                user.setUsername(request.getParameter("Username"));
                user.setSecurityQuestion(request.getParameter("PasswordQuestion"));
                user.setSecurityAnswer(request.getParameter("PasswordSec"));
                                
                if(database.checkData("SELECT Username FROM Users WHERE Username ='" 
                    + user.getUsername() + "'") &&
                        !database.getField("SELECT Username FROM Users WHERE ID =" +
                                user.getId()).equals(user.getUsername())){
                    request.setAttribute("Exception", "Username exists");
                    request.getRequestDispatcher("Profile.jsp").forward(request, response);
                
                } else if(database.executeQueries("UPDATE Users SET Firstname ='"+user.getFirstName()+"'"
                        +", Lastname ='"+user.getLastName()+"', Email ='"+user.getEmail()+"'"
                                + ", Username ='"+user.getUsername()+"', Password ='"+user.getPassword()+"'"
                                        + ", SecurityQuestion ='"+user.getSecurityQuestion()+"'"
                                                + ", SecurityAnswer ='"+user.getSecurityAnswer()+"'"
                                                        + " WHERE ID =" +user.getId())){
                    request.setAttribute("Result", "Profile has been updated");
                    request.getRequestDispatcher("Profile.jsp").forward(request, response);
                }
            } catch(Exception e){
                request.setAttribute("Exception", e.getMessage());
                request.getRequestDispatcher("Pofile.jsp").forward(request, response);
            }
        }
    }
    
    private UserBean getFromDatabase(UserBean user, UserBean login, DatabaseBean database) {
        
        try(Statement stStatement = database.databaseConnection().createStatement();
                ResultSet rs = stStatement.executeQuery(
                    "SELECT ID, Firstname, Lastname, Email, Password, Username, SecurityQuestion, SecurityAnswer FROM Users WHERE ID ="+ login.getId())){
                        
            while(rs.next()){
                user = new UserBean(rs.getInt("ID"), rs.getString("Firstname"), rs.getString("Lastname"),
                        rs.getString("Email"), rs.getString("Password"), rs.getString("Username"),
                        rs.getString("SecurityQuestion"), rs.getString("SecurityAnswer"));
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
