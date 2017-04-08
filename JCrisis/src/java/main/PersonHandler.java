package main;

/**
 * This class handles authenticating the user.
 * 
 * @author Tim Lansing
 */
public class PersonHandler {

    /**
     * The user name for this user
     */
    private String userName = "user";

    /**
     * The password for this user
     */
    private String password = "password";

    /**
     * Denotes whether this Person his logged in
     */
    private boolean loggedIn = false;

    /**
     * A simple test to see if the user name and password represent a valid user
     * 
     * @param userName
     * @param password
     * @return 
     */
    public boolean isValidUser(String userName, String password) {
        if (this.userName.equals(userName) && this.password.equals(password)) {
            loggedIn = true;
        } else {
            loggedIn = false;
        }
        return loggedIn;
    }

    /**
     * Gets the user name
     * @return 
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
     * @return 
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }
}
