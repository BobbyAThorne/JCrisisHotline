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
public class User {

    /**
     * ID of a User.
     */
    int ID;
    /**
     * UserName of a User.
     */
    String userName;
    /**
     * First Name of a User.
     */
    String firstName;
    /**
     * Last name of a User.
     */
    String lastName;
    /**
     * Phone Number of a User.
     */
    String phone;
    /**
     * Address One of a User.
     */
    String addressOne;
    /**
     * Address Two of a User.
     */
    String addressTwo;
    /**
     * City of a User.
     */
    String city;
    /**
     * Territory of a User.
     */
    String territory;
    /**
     * Zip Code of a User.
     */
    String zip;
    /**
     * User roles
     */
    private ArrayList<String> roles;

    /**
     * Get the User ID
     *
     * @return ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Set the User ID
     *
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Get First Name of a User.
     *
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set First Name of a User.
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get Last Name of a User.
     *
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set Last Name of a User.
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get Phone Number of a User.
     *
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set the Phone Number of a User.
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets AddressOne of a User.
     *
     * @return addressOne
     */
    public String getAddressOne() {
        return addressOne;
    }

    /**
     * Set Address One of a User.
     *
     * @param addressOne
     */
    public void setAddressOne(String addressOne) {
        this.addressOne = addressOne;
    }

    /**
     * Get AddressTwo of a User.
     *
     * @return return addressTwo;
     */
    public String getAddressTwo() {
        return addressTwo;
    }

    /**
     * Set Address Two of a User.
     *
     * @param addressTwo
     */
    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    /**
     * Get City of a User
     *
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Set City of a User.
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get Territory of a User
     *
     * @return territory
     */
    public String getTerritory() {
        return territory;
    }

    /**
     * Set Territory of a User.
     *
     * @param territory
     */
    public void setTerritory(String territory) {
        this.territory = territory;
    }

    /**
     * Get the Zip Code of a User.
     *
     * @return zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * Set the Zip Code of a User.
     *
     * @param zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Empty User Constructor.
     */
    public User() {
    }

    /**
     * Full User Constructor.
     *
     * @param ID
     * @param userName
     * @param firstName
     * @param lastName
     * @param phone
     * @param addressOne
     * @param addressTwo
     * @param city
     * @param territory
     * @param zip
     */
    public User(int ID, String userName, String firstName, String lastName, String phone, String addressOne, String addressTwo, String city, String territory, String zip) {
        this.ID = ID;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.addressOne = addressOne;
        this.addressTwo = addressTwo;
        this.city = city;
        this.territory = territory;
        this.zip = zip;
    }

    /**
     * Get the UserName of a User.
     *
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the UserName of a User.
     *
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the roles
     */
    public ArrayList<String> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }
}
