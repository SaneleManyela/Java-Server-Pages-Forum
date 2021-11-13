/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.CategoryBean;
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
@WebServlet(name = "TopicsServlet", urlPatterns = {"/TopicsServlet"})
public class TopicsServlet extends HttpServlet {

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
        
        ArrayList<TopicBean> lstTopics = new ArrayList<>();
        ArrayList<UserBean> lstUsers = new ArrayList<>();
        ArrayList<CategoryBean> lstCategories = new ArrayList<>();
        
        DatabaseBean database =  new DatabaseBean();
        HttpSession session = request.getSession();
        CategoryBean catId = new CategoryBean();
        
        if(request.getParameter("Topic") == null){
            
            catId.setId(Integer.parseInt(request.getParameter("CategoryId")));
    
            if(database.checkData("SELECT * FROM Topics WHERE Category =" + catId.getID())){
                getFromDatabase(catId, database, lstTopics, lstUsers, lstCategories);
                session.setAttribute("Topics", lstTopics);
                session.setAttribute("Users", lstUsers);
                session.setAttribute("TopicCat", lstCategories);
                response.sendRedirect("Topics.jsp");
            } else {
                session.setAttribute("Topics", lstTopics);
                response.sendRedirect("Topics.jsp");
            }
        } else {
            try {
                TopicBean topic = new TopicBean();
                topic.setCategory(Integer.parseInt(request.getParameter("TopicCategory")));
                topic.setTitle(request.getParameter("Topic"));
                topic.setDescription(request.getParameter("TopicDescription"));
                topic.setPostBy(Integer.parseInt(request.getParameter("PostedBy")));
                topic.setTopicDate(database.getField("SELECT CURRENT_TIMESTAMP"));
                    
                if(database.executeQueries("INSERT INTO Topics(Category, Title, Description, PostedBy, TopicDate)"
                        + "VALUES('"+topic.getCategory()+"','"+topic.getTitle()+"','"+topic.getDescription()+"','"
                        +topic.getPostBy()+"','"+topic.getTopicDate()+"')")){
                    request.setAttribute("Result", "Topic has been posted");
                    request.getRequestDispatcher("NewPost.jsp").forward(request, response);
                }
            } catch(Exception ex){
                request.setAttribute("Exception", ex.getMessage());
                response.sendRedirect("NewPost.jsp");
            }
        }
    }
    
    private void getFromDatabase(CategoryBean catId, DatabaseBean database, ArrayList<TopicBean> lstTopics,
            ArrayList<UserBean> lstUsers, ArrayList<CategoryBean> lstCategories) {
        
        try(Statement stStatement = database.databaseConnection().createStatement();
                ResultSet rs = stStatement.executeQuery(
                    "SELECT ID, Category, Title, Description, PostedBy, TopicDate FROM Topics WHERE Category =" + catId.getID())){
                        
            while(rs.next()){
                lstTopics.add(new TopicBean(rs.getInt("ID"), rs.getInt("Category"), 
                        rs.getString("Title"), rs.getString("Description"), rs.getInt("PostedBy"), rs.getString("TopicDate"))); 
            }
            
            stStatement.close();
            rs.close();
            
            for(TopicBean topic : lstTopics){
                lstUsers.add(new UserBean(database.getField("SELECT Username FROM Users WHERE ID =" + topic.getPostBy())));
                lstCategories.add(new CategoryBean(database.getField("SELECT CategoryName FROM Categories WHERE Category =" + topic.getCategory())));
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
