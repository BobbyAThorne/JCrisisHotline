/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accessors;

import Beans.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author DragonSheep
 */
public class UserAccessor {
    public static ArrayList<User> getUserList() throws SQLException {
        ArrayList<User> userList = new ArrayList();
        try(Connection conn = Connector.createDBConnection()){
            CallableStatement sp_retrieve_user_list = 
                  conn.prepareCall("Call sp_retrieve_user_list");
            ResultSet resultSet = sp_retrieve_user_list.executeQuery();
            while(resultSet.next()) {
                userList.add(new User(
                    resultSet.getInt("User_ID")
                    ,resultSet.getString("First_Name")
                    ,resultSet.getString("Last_Name")
                    ,resultSet.getString("Phone")
                    ,resultSet.getString("Address_One")
                    ,resultSet.getString("Address_Two")
                    ,resultSet.getString("City")
                    ,resultSet.getString("Territory")
                    ,resultSet.getString("Zip")
                ));
            }
        } catch(SQLException ex) {
            throw ex;
        }
        return userList;
    }
    
    /**
     * Eric Walton
     * 2017/11/04
     * @param userID
     * @param password
     * @return
     * @throws SQLException 
     */
    public static boolean validateUser(int userID, String password) throws SQLException{
        boolean result = false;
        try(Connection conn = Connector.createDBConnection()){
            CallableStatement validateUser = 
                  conn.prepareCall("{? = CALL sp_validate_user(?)}");
            validateUser.registerOutParameter(userID, Types.INTEGER);
            validateUser.registerOutParameter(password, Types.VARCHAR);
            ResultSet resultSet = validateUser.executeQuery();
            resultSet.next();
            if (resultSet.getInt("") == 1) {
                result = true;
            }
            
        } catch(SQLException ex) {
            throw ex;
        }
        return result;
    }
    
}
