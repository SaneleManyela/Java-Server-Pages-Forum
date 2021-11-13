/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 * Class   : CommentBean 
 * Created on : 28 Oct 2021, 4:59:04 PM
 * Description : This class models a comments database table.
 * @author : Sanele
 */
public class CommentBean implements java.io.Serializable {
    private int id;
    private int topic;
    private String comment;
    private int commentBy;
    private String commentDate;
    
    /**
     * Creates an object of type CommentBean
     */
    public CommentBean(){
    }
        
    /**
     * A constructor used for assigning bean properties on object declaration
     * @param id : the id of a comment.
     * @param topic : a reference to the topic a comment is under.
     * @param comment : the content of a comment.
     * @param commentBy : a reference to the user who posted the comment.
     * @param commentDate : the date on which the comment was posted.
     */
    public CommentBean(int id, int topic, String comment, int commentBy, String commentDate) {
        this.id = id;
        this.topic = topic;
        this.comment = comment;
        this.commentBy = commentBy;
        this.commentDate = commentDate;
    }
    
    /**
     * A constructor to set on declaration the content of a comment
     * @param comment : the content of a comment.
     */
    public CommentBean(String comment) {
        this.comment = comment;
    }
    
    /**
     * A method to get an id associated with a comment in the database
     * @return : an id of a comment.
     */
    public int getId() {
        return id;
    }
    
    /**
     * A method to set the id of a comment
     * @param id : comment id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * A method to get a topic id
     * @return : topic id
     */
    public int getTopic() {
        return topic;
    }
    
    /**
     * A method to set a topic id
     * @param topic 
     */
    public void setTopic(int topic) {
        this.topic = topic;
    }
    
    /**
     * A method that returns a comment
     * @return : a comment String
     */
    public String getComment() {
        return comment;
    }
    
    /**
     * A method that sets a comment String
     * @param comment : the content of the comment being set
     * @throws Exception : when a user enters an empty comment
     */
    public void setComment(String comment) throws Exception {
        if(comment.trim() == null){
            throw new Exception("Enter comment");
        } else {
            this.comment = comment;
        }
    }
    
    /**
     * A method that returns a user that commented
     * @return : the identifier of a user that commented
     */
    public int getCommentBy() {
        return commentBy;
    }
    
    /**
     * A method that sets an ID for the user that commented
     * @param commentBy : The ID of the user that comments
     */
    public void setCommentBy(int commentBy) {
        this.commentBy = commentBy;
    }
    
    /**
     * A method to get a date of the comment
     * @return : a date of a comment
     */
    public String getCommentDate() {
        return commentDate;
    }
    
    /**
     * A method to set the date of a comment
     * @param commentDate : The date of a comment
     */
    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }
}
