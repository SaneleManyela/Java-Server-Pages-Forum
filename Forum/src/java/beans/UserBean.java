/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 *
 * @author Sanele
 */
public class UserBean implements Serializable{
    
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;
    private String securityQuestion;
    private String securityAnswer;
    
    public UserBean() {
    }
    
    public UserBean(int id, String firstName, String lastName, String email,
            String password, String username, String securityQuestion, String securityAnswer) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.username = username;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }
    
    public UserBean(String username) {
        this.username = username;
    }
    
    public UserBean(String password, String securityQuestion, String securityAnswer){
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstname) throws Exception {
        if(firstname.trim() == null){
            throw new Exception("Enter first name!!");
        } else if(firstname.trim().length() > 55){
            throw new Exception("First name must be 55 characters!!");
        } else {
            this.firstName = firstname;
        }
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) throws Exception{
        if(lastName.trim() == null){
            throw new Exception("Enter last name!!");
        } else if(lastName.trim().length() > 55){
            throw new Exception("Last name must be 55 characters!!");
        } else {
            this.lastName = lastName;
        }
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email) throws Exception {
        if(email.trim() != null){
            if(email.trim().length() > 30){
                throw new Exception("Enter a 30 character email!!");
            } else if(Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$").
                    matcher(email).matches()) {
                this.email = email;
            } else {
                throw new Exception("Entered email is invalid");
            }
        }
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) throws Exception {
        if(password.trim() == null){
            throw new Exception("Enter password");
        } else if(password.trim().length() < 7){
            throw new Exception("A password must be at least 7 characters!");
        } else {
            this.password = password;
        }
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) throws Exception {
        if(username.trim() == null){
            throw new Exception("Enter username");
        } else if(username.trim().length() > 15){
            throw new Exception("Username must be at least 15 characters or less");
        } else {
            this.username = username;
        }
    }
    
    public String getSecurityQuestion() {
        return securityQuestion;
    }
    
    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }
    
    public String getSecurityAnswer() {
        return securityAnswer;
    }
    
    public void setSecurityAnswer(String securityAnswer) throws Exception {
        if(securityAnswer.trim() == null){
            throw new Exception("Enter security question answer");
        } else {
            this.securityAnswer = securityAnswer;
        }
    }
}
