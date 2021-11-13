/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

/**
 *
 * @author Sanele
 */
public class TopicBean implements Serializable {
    private int id;
    private int category;
    private String title;
    private String description;
    private int postBy;
    private String topicDate;
    
    public TopicBean() {
    }
    
    public TopicBean(int id, int category, String title,
            String description, int postBy, String topicDate) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.description = description;
        this.postBy = postBy;
        this.topicDate = topicDate;
    }
    
    public TopicBean(String title) {
        this.title = title;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) throws Exception {
        this.id = id;
    }
    
    public int getCategory() {
        return this.category;
    }
    
    public void setCategory(int category) {
        this.category = category;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) throws Exception {
        if(title.trim() == null){
            throw new Exception("Enter topic title");
        } else {
            this.title = title;
        }
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) throws Exception {
        if(description.trim() == null){
            throw new Exception("Enter topic description");
        } else {
            this.description = description;
        }
    }

    public int getPostBy() {
        return this.postBy;
    }
    
    public void setPostBy(int postBy) {
        this.postBy = postBy;
    }
    
    public String getTopicDate() {
        return topicDate;
    }
    
    public void setTopicDate(String topicDate) {
        this.topicDate = topicDate;
    }
}