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

    private char[] oldPassword;

    private char[] newPassword;

    private String errorMessage;

    /**
     * Empty constructor.
     */
    public ChangePasswordBean() {

    }

    /**
     * *
     * Full ChangePasswordBean constructor.
     *
     * @param oldPassword
     * @param newPassword
     */
    public ChangePasswordBean(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword.toCharArray();
        this.newPassword = newPassword.toCharArray();
    }

    /**
     * @return the oldPassword
     */
    public char[] getOldPassword() {
        return oldPassword;
    }

    /**
     * @param oldPassword the oldPassword to set
     */
    public void setOldPassword(char[] oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * @return the newPassword
     */
    public char[] getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(char[] newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
