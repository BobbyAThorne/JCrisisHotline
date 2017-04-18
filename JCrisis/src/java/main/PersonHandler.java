package main;

import Accessors.UserAccessor;

/**
 * This class handles authenticating the user.
 *
 * @author Tim Lansing
 */
public class PersonHandler {

    /**
     * The user name for this user
     */
    private String userName = "";

    /**
     * The password for this user
     */
    private String password = "";

    /**
     * Denotes whether this Person his logged in
     */
    private boolean loggedIn = false;

    /**
     * A simple test to see if the user name and password represent a valid user
     * Modified by Eric Walton 2017/11/04
     *
     * @param userName
     * @param password
     * @return loggedIn
     */
    public boolean isValidUser(String userName, String password) {
        if (userName.length() > 0) {
            int id = Integer.parseInt(userName);
            try {
                if (id == 10000 && password.equals("password")) { //UserAccessor.validateUser(id, password)
                    loggedIn = true;
                    this.userName = userName;
                } else {
                    loggedIn = false;
                }
            } catch (Exception e) {
                System.out.println("eric " + e);
            }
        }

        return loggedIn;
    }

    /**
     * Gets the user name
     *
     * @return name
     */
    public String getUserName() {
        String name = "";
        if (loggedIn) {
            name = userName;
        }
        return name;
    }

    /**
     * Returns whether this user is logged in.
     *
     * @return loggedIn
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }
}
