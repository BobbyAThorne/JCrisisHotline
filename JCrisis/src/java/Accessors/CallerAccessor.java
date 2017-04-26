/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accessors;

import Beans.CallRecord;
import Beans.Caller;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author Eric Walton
 */
public class CallerAccessor {

    public static Boolean createCallRecord(Caller caller, CallRecord callRecord) throws SQLException {
        Boolean result = false;
        Boolean exist = false;
        int callerID;

        Connection conn = Connector.createDBConnection();
        CallableStatement checkForCaller
                = conn.prepareCall("{CALL sp_check_caller_exist(?,?,?)}");

        checkForCaller.setString(1, caller.getFirstName());
        checkForCaller.setString(2, caller.getLastName());
        checkForCaller.setString(3, caller.getPhone());
        ResultSet resultSet = checkForCaller.executeQuery();

        if (resultSet.next()) {
            caller.setCallerID(resultSet.getInt("Caller_ID"));
        } else {
            createCaller(caller);
        }
        
        
//        try (Connection conn = Connector.createDBConnection()) {
//            CallableStatement createCallRecord = conn.prepareCall("{Call sp_create_callrecord()}");
//        } catch (Exception e) {
//        }
        return result;
    }

    public static int createCaller(Caller caller) throws SQLException {
        Connection conn = Connector.createDBConnection();
        CallableStatement createCaller
                = conn.prepareCall("{CALL sp_create_caller(?,?,?,?,?,?,?)}");
        createCaller.setString(1, caller.getFirstName());
        createCaller.setString(2, caller.getLastName());
        createCaller.setString(3, caller.getPhone());
        createCaller.setString(4, caller.getAddress());
        createCaller.setString(5, caller.getCity());
        createCaller.setString(6, caller.getState());
        createCaller.setString(7, caller.getZip());
        ResultSet resultSet = createCaller.executeQuery();
        if (resultSet.next()) {
            caller.setCallerID(resultSet.getInt("new_id"));
            System.out.println("test");
        }
        return caller.getCallerID();
    }

    public static CallRecord insertCallRecord(Caller caller, CallRecord callRecord) throws SQLException{
        CallRecord newCallRecord;
        Connection conn = Connector.createDBConnection();
        CallableStatement createCallRecord
                = conn.prepareCall("{CALL sp_create_callrecord(?,?,?,?,?,?,?)}");
        
        
        return callRecord;
    }
    
    
} // end of class
