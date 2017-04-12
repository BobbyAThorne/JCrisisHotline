/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.ArrayList;

/**
 *
 * @author DragonSheep
 */
public class UserPageBean {
    ArrayList<User> userList;
    User currentUser;
    ArrayList<String> roles;
    String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public UserPageBean() {
        errorMessage = "";
    }

    public UserPageBean(ArrayList<User> userList, User currentUser) {
        this.userList = userList;
        this.currentUser = currentUser;
        errorMessage="";
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> Roles) {
        this.roles = Roles;
    }
    
    public boolean isDataEntry() {
        return null!=roles&&roles.contains("Data Entry");
    }
    
    public boolean isManager() {
        return null!=roles&&roles.contains("Manager");
    }
}
