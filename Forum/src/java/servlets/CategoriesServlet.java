/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.CategoryBean;
import beans.DatabaseBean;
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
@WebServlet(name = "CategoriesServlet", urlPatterns = {"/CategoriesServlet"})
public class CategoriesServlet extends HttpServlet {

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
        ArrayList<CategoryBean> lstCategories = new ArrayList<>();
        DatabaseBean database = new DatabaseBean();
        HttpSession session;
        
        
        if(request.getParameter("Category") == null){
            if(request.getParameter("Post") != null) {
                
                getFromDatabase(database, lstCategories);
                session = request.getSession();
                session.setAttribute("Categories", lstCategories);
                response.sendRedirect("NewPost.jsp");
                
            } else if(database.checkData("SELECT * FROM Categories")) {
                getFromDatabase(database, lstCategories);
                session = request.getSession();
                session.setAttribute("Categories", lstCategories);
                response.sendRedirect("Categories.jsp");
                    
            } else {
                    
                database.executeQueries("INSERT INTO Categories(CategoryName, Description)"
                        + "VALUES('Java Arrays','Java array is an object which contains elements "
                        + "of a similar data type. Additionally, The elements of an array are "
                        + "stored in a contiguous memory location. It is a data structure where "
                        + "we store similar elements. We can store only a fixed set of elements "
                        + "in a Java array.')");
                
                database.executeQueries("INSERT INTO Categories(CategoryName, Description)"
                        + "VALUES('Java Collections', 'The Java collections framework provides "
                        + "various algorithms that can be used to manipulate elements stored "
                        + "in data structures.')");
                    
                database.executeQueries("INSERT INTO Categories(CategoryName, Description)"
                        + "VALUES('C# Classes','C# classes are data structures that combine "
                        + "various types of data members such as fields, properties, member "
                        + "functions, and events into a single unit.')");
                    
                getFromDatabase(database, lstCategories);
                session = request.getSession();
                session.setAttribute("Categories", lstCategories);
                response.sendRedirect("Categories.jsp");
            }
        } else {
            try{
                CategoryBean category = new CategoryBean();
                category.setCategoryName(request.getParameter("Category"));
                category.setDescription(request.getParameter("Description"));
                
                if(database.checkData("SELECT CategoryName FROM Categories WHERE CategoryName ='"+category.getCategoryName()+"'")){
                    request.setAttribute("Exception", "Category Exists");
                    request.getRequestDispatcher("AddCategory.jsp").forward(request, response);
                    
                } else if(database.executeQueries("INSERT INTO Categories(CategoryName, Description)"
                        + "VALUES('"+category.getCategoryName()+"','"+category.getDescription()+"')")) {
                    request.setAttribute("Result", "Category added");
                    request.getRequestDispatcher("AddCategory.jsp").forward(request, response);
                }
            } catch(Exception ex) {
                request.setAttribute("Exception", ex.getMessage());
                response.sendRedirect("AddCategory.jsp");
            }
        }
    }
    
    private void getFromDatabase(DatabaseBean database, ArrayList<CategoryBean> lst) {
        try(Statement stStatement = database.databaseConnection().createStatement();
                ResultSet rs = stStatement.executeQuery(
                    "SELECT Category, CategoryName, Description FROM Categories")){
                        
            while(rs.next()){
                lst.add(new CategoryBean(rs.getInt("Category"),
                rs.getString("CategoryName"), rs.getString("Description"))); 
            }
            stStatement.close();
            rs.close();
        } catch(Exception e) {
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
