/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class    : DatabaseBean
 * Created on : 28 Oct 2021, 9:55:15 AM
 * Description : This class provides methods to connect 
 *               to the database, retrieve, and update data. 
 * @author  : Sanele
 */
public class DatabaseBean implements Serializable {
    /**
     * A method that return a database connection
     * @return Connection : a database connection
     */
    public Connection databaseConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Forum;user=sa;password=password");
        } catch(SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    /**
     * A method that checks if data exists in the database 
     * tables.
     * @param strQuery : an SQL Query String
     * @return boolean : a true or false value
     */
    public boolean checkData(String strQuery) {
        Connection conConnection = databaseConnection();
        boolean isExisting = false;
        
        try(Statement stStatement = conConnection.createStatement()) {
            
            isExisting = stStatement.executeQuery(strQuery).next();
            
            stStatement.close();
            conConnection.close();
            
            return isExisting;
        } catch(SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        
        } finally {
            try{
                conConnection.close();
            } catch(SQLException | NullPointerException e) {
            }
        }
        return isExisting;
    }
    
    /**
     * A method that execute insert and update 
     * @param strQuery : an SQL Query String
     * @return boolean : a true or false value
     */
    public boolean executeQueries(String strQuery) {
        Connection conConnection = databaseConnection();
        boolean isExecuted = false;
        
        try(Statement stStatement = conConnection.createStatement()) {
            
            stStatement.executeUpdate(strQuery);          
            isExecuted = true;
  
            stStatement.close();
            conConnection.close();
            
            return isExecuted;
        } catch(SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        
        } finally {
            try{
                conConnection.close();
            } catch(SQLException | NullPointerException e) {
            }
        }
        return isExecuted;
    }
    
    public String getField(String strQuery) {
        Connection conConnection = databaseConnection();
        
        try(Statement stStatement = conConnection.createStatement();
                ResultSet rs = stStatement.executeQuery(strQuery)) {
                while(rs.next()) {
                    return rs.getString(1);
                }
        } catch(SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        
        } finally {
            try{
                conConnection.close();
            } catch(SQLException | NullPointerException e) {
            }
        }
        return "";
    }
}
