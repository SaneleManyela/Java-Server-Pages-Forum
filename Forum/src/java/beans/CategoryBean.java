/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

/**
 * Class   : CategoryBean 
 * Created on : 28 Oct 2021, 4:56:32 PM
 * Description : This class models a category database table.
 * @author : Sanele
 */
public class CategoryBean implements Serializable {
    public CategoryBean(){
    }
    
    /**
     * A constructor used for assigning bean properties on object declaration
     * @param id : an id associated with a category in the database.
     * @param categoryName : a name of the category.
     * @param description : a description of a category
     */
    public CategoryBean(int id, String categoryName, String description) {
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
    }
    
    /**
     * A constructor used to the the name property of a category
     * @param categoryName : the name of the category
     */
    public CategoryBean(String categoryName) {
        this.categoryName = categoryName;
    }
    
    private int id; // a field to store the id of a category
    private String categoryName; // a field to store the name of a category
    private String description; // a field to store the description of a category
    
    /**
     * A method to get the id of a category
     * @return : returns category id
     */
    public int getID() {
        return id;
    }
    
    /**
     * A method to set a category id
     * @param id : the id of a category
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * A method to get a category name
     * @return : the name of a category
     */
    public String getCategoryName() {
        return categoryName;
    }
    
    /**
     * A method to set a category name
     * @param categoryName : the name of a category
     * @throws Exception : if the category name is null
     */
    public void setCategoryName(String categoryName) throws Exception {
        if(categoryName.trim() == null){
            throw new Exception("Enter Category name");
        } else {
            this.categoryName = categoryName;
        }
    }
    
    /**
     * A method to get the description of a category
     * @return : the description of a category
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * A method to set the description of a category
     * @param description : the description of a category
     * @throws Exception : if the category description is null
     */
    public void setDescription(String description) throws Exception {
        if(description.trim() == null){
            throw new Exception("Enter Category description");
        } else {
            this.description = description;
        }
    }
}
