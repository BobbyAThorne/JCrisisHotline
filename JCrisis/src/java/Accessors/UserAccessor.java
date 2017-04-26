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
 * User Data Accessors.
 *
 * @author DragonSheep
 *
 * Updated: 2017/04/18 By: Alissa Duffy Standardized Commenting.
 */
public class UserAccessor {

    /**
     * DragonSheep Returns a List of Active Users.
     *
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
                        resultSet.getString("UserName"),
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
     * Eric Walton 2017/11/04 Validates a User.
     *
     * @param userID
     * @param password
     * @return
     * @throws SQLException
     */
    public static boolean validateUser(String userName, String password) throws SQLException {
        boolean result = false;
        try (Connection conn = Connector.createDBConnection()) {
            CallableStatement validateUser
                    = conn.prepareCall("{CALL sp_validate_user(?,?)}");
            validateUser.setString(1, userName);
            validateUser.setString(2, password);
            ResultSet resultSet = validateUser.executeQuery();
            resultSet.next();
            if (resultSet.getInt("UserCount") == 1) {
                result = true;
            }
            //This is the start to change the return value as a user rather that bool
            //if(result){
                //UserAccessor.retrieveUserbyUsername(userName);
            //}
        } catch (SQLException ex) {

            throw ex;
        }
        return result;
    }

    /**
     *
     * Updates a User's Password.
     *
     * @param newPasswordHash
     * @param newPasswordSalt
     * @param userId
     * @return
     * @throws SQLException
     */
    public static boolean updatePassword(String newPasswordHash, String newPasswordSalt, int userId) throws SQLException {
        boolean result = false;

        try (Connection conn = Connector.createDBConnection()) {
            CallableStatement updatePassword
                    = conn.prepareCall("CALL sp_update_password(?, ?, ?)");

            updatePassword.registerOutParameter(1, JDBCType.INTEGER);
            updatePassword.registerOutParameter(2, JDBCType.CHAR, 88);
            updatePassword.registerOutParameter(3, JDBCType.CHAR, 88);

            updatePassword.setInt(1, userId);
            updatePassword.setString(2, newPasswordHash);
            updatePassword.setString(3, newPasswordSalt);

            result = 1 == updatePassword.executeUpdate();

        } catch (SQLException ex) {

            throw ex;
        }
        return result;
    }

    /**
     * Alissa Duffy Creates a New User.
     *
     * @param newUser
     * @return success
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

    /**
     * Retrieves Password Salt.
     *
     * @param userID
     * @return
     * @throws SQLException
     */
     public static String retrievePasswordSalt(int userID) throws SQLException {
        String salt = null;
        try (Connection conn = Connector.createDBConnection()) {
            CallableStatement retrievePasswordSalt
                    = conn.prepareCall("{? =CALL sp_retrieve_salt(?)}");
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

    /**
     *
     * Retrieves Password Hash.
     *
     * @param userID
     * @return
     * @throws SQLException
     */
    public static String retrievePasswordHash(int userID) throws SQLException {
        String hash = null;
        try (Connection conn = Connector.createDBConnection()) {
            CallableStatement retrievePasswordHash
                    = conn.prepareCall("{? = CALL sp_retrieve_hash(?)}");
            
            retrievePasswordHash.setInt("p_User_ID", userID);
            retrievePasswordHash.registerOutParameter("Password_Hash", JDBCType.CHAR, 88);
            
            ResultSet resultSet = retrievePasswordHash.executeQuery();
            if (resultSet.next()) {
                hash = resultSet.getString("Password_Hash");
            }

        } catch (SQLException ex) {
            throw ex;
        }
        return hash;

    }
    
    public static String retrievePasswordHash(String userName) throws SQLException {
        String hash = null;
        try (Connection conn = Connector.createDBConnection()) {
            CallableStatement retrievePasswordHash
                    = conn.prepareCall("{? = CALL sp_retrieve_hash_by_username(?)}");
            
            retrievePasswordHash.setString("p_UserName", userName);
            retrievePasswordHash.registerOutParameter("Password_Hash", JDBCType.CHAR, 88);
            
            ResultSet resultSet = retrievePasswordHash.executeQuery();
            if (resultSet.next()) {
                hash = resultSet.getString("Password_Hash");
            }

        } catch (SQLException ex) {
            throw ex;
        }
        return hash;
    }

    /**
     * Retrieves and Array List of User Roles according to the User's Id.
     *
     * @param userID
     * @return
     * @throws SQLException
     */
    public static ArrayList<String> retrieveUserRoles(int userID) throws SQLException {
        ArrayList<String> roleList = new ArrayList();
        try (Connection conn = Connector.createDBConnection()) {
            CallableStatement retrievePasswordHash
                    = conn.prepareCall("CALL sp_retrieve_user_roles(?)");
            retrievePasswordHash.registerOutParameter(1, JDBCType.INTEGER);
            retrievePasswordHash.setInt(1, userID);

            ResultSet resultSet = retrievePasswordHash.executeQuery();
            while (resultSet.next()) {
                roleList.add(resultSet.getString("Role_ID"));
            }

        } catch (SQLException ex) {
            throw ex;
        }
        return roleList;

    }

    /**
     * William Flood Creates a New User.
     *
     * @param newUser
     * @return success
     * @throws SQLException
     */
    public static boolean updateUser(User toUpdate) throws SQLException {
        boolean success = false;

        try (Connection conn = Connector.createDBConnection()) {
            CallableStatement createUser
                    = conn.prepareCall("{CALL sp_update_user(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            createUser.setInt(1, toUpdate.getID());
            createUser.setString(2, toUpdate.getUserName());
            createUser.setString(3, toUpdate.getFirstName());
            createUser.setString(4, toUpdate.getLastName());
            createUser.setString(5, toUpdate.getPhone());
            createUser.setString(6, toUpdate.getAddressOne());
            createUser.setString(7, toUpdate.getAddressTwo());
            createUser.setString(8, toUpdate.getCity());
            createUser.setString(9, toUpdate.getTerritory());
            createUser.setString(10, toUpdate.getZip());

            int results = createUser.executeUpdate();
            if (results == 1) {
                success = true;
            }

        } catch (SQLException ex) {
            throw ex;
        }
        return success;
    }

    /**
     * William Flood Creates a New User.
     *
     * @param newUser
     * @return success
     * @throws SQLException
     */
    public static boolean updateUserRoles(int userID,
            boolean isReportsUser,
            boolean isCounselor,
            boolean isManager,
            boolean isDataEntry) throws SQLException {
        boolean success = false;

        try (Connection conn = Connector.createDBConnection()) {
            CallableStatement createUser
                    = conn.prepareCall("{CALL sp_update_user_roles(?, ?, ?, ?, ?)}");
            createUser.setInt(1, userID);
            createUser.setBoolean(2, isReportsUser);
            createUser.setBoolean(3, isCounselor);
            createUser.setBoolean(4, isManager);
            createUser.setBoolean(5, isDataEntry);

            int results = createUser.executeUpdate();
            if (results == 1) {
                success = true;
            }

        } catch (SQLException ex) {
            throw ex;
        }
        return success;
    }
    
    public static String retrievePasswordSalt(String userName) throws SQLException {
        String salt = null;
        try (Connection conn = Connector.createDBConnection()) {
            CallableStatement retrievePasswordSalt
                    = conn.prepareCall("{CALL sp_retrieve_salt_by_username(?)}");
            retrievePasswordSalt.setString(1, userName);

            ResultSet resultSet = retrievePasswordSalt.executeQuery();
            if (resultSet.next()) {
                salt = resultSet.getString("Password_Salt");
            }

        } catch (SQLException ex) {
            throw ex;
        }

        return salt;
    }
}
