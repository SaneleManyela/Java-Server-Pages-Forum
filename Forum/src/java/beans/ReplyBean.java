/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Sanele
 */
public class ReplyBean implements java.io.Serializable {
    
    private int comment;
    private int replyBy;
    private String reply;
    private String replyDate;
    
    public ReplyBean() { 
    }
    
    public ReplyBean(int comment, int replyBy, String reply, String replyDate) {
        this.comment = comment;
        this.replyBy = replyBy;
        this.reply = reply;
        this.replyDate = replyDate;
    }
    
    public int getComment() {
        return comment;
    } 
    
    public void setComment(int comment) {
        this.comment = comment;
    }
    
    public int getReplyBy() {
        return replyBy;
    }
    
    public void setReplyBy(int replyBy) {
        this.replyBy = replyBy;
    }
    
    public String getReply() {
        return reply;
    }
    
    public void setReply(String reply) throws Exception {
        if(reply.trim() == null){
            throw new Exception("Enter reply content");
        } else {
            this.reply = reply;
        }
    }
    
    public String getReplyDate() {
        return replyDate;
    }
    
    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }
}
