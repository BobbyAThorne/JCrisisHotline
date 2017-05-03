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
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Eric Walton
 */
public class CallerAccessor {

    public static int createCallRecord(Caller caller, CallRecord callRecord) throws SQLException {
        int result = 0;
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
        result = insertCallRecord(caller, callRecord);

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
            System.err.println("setCallerID: " + caller.getCallerID());
        }else{
            System.err.println("Couldn't get caller id");
        }
        return caller.getCallerID();
    }

    public static int insertCallRecord(Caller caller, CallRecord callRecord) throws SQLException {
        int newCallerID = 0;
        System.err.println("start of call record: " + caller.getCallerID());
        
        Connection conn = Connector.createDBConnection();
        CallableStatement createCallRecord
                = conn.prepareCall("{CALL sp_create_call_record(?,?,?,?,?)}");
        createCallRecord.setString(1, callRecord.getStartTime());
        createCallRecord.setInt(2, callRecord.getCounselorID());
        createCallRecord.setString(3, callRecord.getCallDescription());
        createCallRecord.setString(4, callRecord.getCallTypeID());
        createCallRecord.setInt(5, caller.getCallerID());
        ResultSet resultSet = createCallRecord.executeQuery();
         if (resultSet.next()) {
            newCallerID = resultSet.getInt("new_id");
            System.err.println("setCallerRecordID: " + newCallerID);
        }else{
            System.err.println("Couldn't get call record id");
        }

        
        
        return newCallerID;
    }

} // end of class
