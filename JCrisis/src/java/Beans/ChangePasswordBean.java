/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 * Java Bean to Change a User Password.
 *
 * @author Aaron Usher
 *
 * Updated: 2017/04/18 By: Alissa Duffy Standardized Commenting.
 */
public class ChangePasswordBean {

    private String message;

    /**
     * Empty constructor.
     */
    public ChangePasswordBean() {

    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
