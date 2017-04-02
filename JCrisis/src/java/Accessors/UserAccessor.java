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
                  conn.prepareCall("Call tsp_GetPersonById(?)");
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
}
