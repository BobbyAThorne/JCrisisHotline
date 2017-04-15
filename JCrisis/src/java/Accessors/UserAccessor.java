/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accessors;

import Beans.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author DragonSheep
 */
public class UserAccessor {

    /**
     * DragonSheep
     * Returns a List of Active Users.
     * @return userList
     * @throws SQLException 
     */

    public static ArrayList<User> getUserList() throws SQLException {
        ArrayList<User> userList = new ArrayList();
        try (Connection conn = Connector.createDBConnection()) {
            CallableStatement sp_retrieve_user_list
                    = conn.prepareCall("Call sp_retrieve_user_list");
            ResultSet resultSet = sp_retrieve_user_list.executeQuery();
            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getInt("User_ID"),
                         resultSet.getString("First_Name"),
                         resultSet.getString("Last_Name"),
                         resultSet.getString("Phone"),
                         resultSet.getString("Address_One"),
                         resultSet.getString("Address_Two"),
                         resultSet.getString("City"),
                         resultSet.getString("Territory"),
                         resultSet.getString("Zip")
                ));
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return userList;
    }

    /**
     * Eric Walton 2017/11/04
     * Validates a User.
     *
     * @param userID
     * @param password
     * @return
     * @throws SQLException
     */
    public static boolean validateUser(int userID, String password) throws SQLException {
        boolean result = false;
        try (Connection conn = Connector.createDBConnection()) {
            CallableStatement validateUser
                    = conn.prepareCall("{? = CALL sp_validate_user(?)}");
            validateUser.registerOutParameter(userID, Types.INTEGER);
            validateUser.registerOutParameter(password, Types.VARCHAR);
            ResultSet resultSet = validateUser.executeQuery();
            resultSet.next();
            if (resultSet.getInt("") == 1) {
                result = true;
            }

        } catch (SQLException ex) {

            throw ex;
        }
        return result;
    }

    public static boolean updatePassword(String oldPasswordHash, String oldPasswordSalt, String newPasswordHash, String newPasswordSalt, int userId) throws SQLException {
        boolean result = false;

        try (Connection conn = Connector.createDBConnection()) {
            CallableStatement updatePassword
                    = conn.prepareCall("CALL sp_update_password(?, ?, ?, ?, ?)");
            updatePassword.registerOutParameter("p_User_ID", JDBCType.INTEGER);
            updatePassword.registerOutParameter("p_Old_Password_Hash", JDBCType.CHAR, 64);
            updatePassword.registerOutParameter("p_New_Password_Hash", JDBCType.CHAR, 64);
            updatePassword.registerOutParameter("p_Old_Password_Salt", JDBCType.CHAR, 64);
            updatePassword.registerOutParameter("p_New_Password_Salt", JDBCType.CHAR, 64);

            updatePassword.setInt("p_User_ID", userId);
            updatePassword.setString("p_Old_Password_Hash", oldPasswordHash);
            updatePassword.setString("p_New_Password_Hash", newPasswordHash);
            updatePassword.setString("p_Old_Password_Salt", oldPasswordSalt);
            updatePassword.setString("p_New_Password_Salt", newPasswordSalt);

            result = 1 == updatePassword.executeUpdate();

        } catch (SQLException ex) {

            throw ex;
        }
        return result;
    }

    /**
     * Alissa Duffy
     * Creates a New User.
     * @param newUser
     * @return
     * @throws SQLException 
     */
    public static boolean createUser(User newUser) throws SQLException {
        boolean success = false;

        try (Connection conn = Connector.createDBConnection()) {
            CallableStatement createUser
                    = conn.prepareCall("{CALL sp_create_user(?, ?, ?, ?, ?, ?, ?, ?)}");
            createUser.setString(1, newUser.getUserName());
            createUser.setString(2, newUser.getFirstName());
            createUser.setString(3, newUser.getLastName());
            createUser.setString(4, newUser.getPhone());
            createUser.setString(5, newUser.getAddressOne());
            createUser.setString(6, newUser.getAddressTwo());
            createUser.setString(6, newUser.getCity());
            createUser.setString(7, newUser.getTerritory());
            createUser.setString(8, newUser.getZip());

            ResultSet resultSet = createUser.executeQuery();
            if (resultSet.getInt("") == 1) {
                success = true;
                }

        } catch (SQLException ex) {
            throw ex;
        }
        return success;
    }


    public static String retrievePasswordSalt(int userID) throws SQLException {
        String salt = null;
        try (Connection conn = Connector.createDBConnection()) {
            CallableStatement retrievePasswordSalt
                    = conn.prepareCall("CALL sp_retrieve_salt(?)");
            retrievePasswordSalt.registerOutParameter("p_User_Id", JDBCType.INTEGER);
            retrievePasswordSalt.setInt("p_User_ID", userID);

            ResultSet resultSet = retrievePasswordSalt.executeQuery();
            if (resultSet.next()) {
                salt = resultSet.getString("Password_Salt");
            }

        } catch (SQLException ex) {
            throw ex;
        }

        return salt;
    }

    public static String retrievePasswordHash(int userID) throws SQLException {
        String hash = null;
        try (Connection conn = Connector.createDBConnection()) {
            CallableStatement retrievePasswordHash
                    = conn.prepareCall("CALL sp_retrieve_hash(?)");
            retrievePasswordHash.registerOutParameter("p_User_Id", JDBCType.INTEGER);
            retrievePasswordHash.setInt("p_User_ID", userID);

            ResultSet resultSet = retrievePasswordHash.executeQuery();
            if (resultSet.next()) {
                hash = resultSet.getString("Password_Hash");
            }

        } catch (SQLException ex) {
            throw ex;
        }
        return hash;

    }

}
