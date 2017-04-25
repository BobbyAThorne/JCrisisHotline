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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author Eric Walton
 */
public class CallerAccessor {
    
    public static Boolean createCallRecord(Caller caller,CallRecord callRecord) throws SQLException{
        Boolean result = false;
        Boolean exist = false;
        
        try (Connection conn = Connector.createDBConnection()) {
            CallableStatement checkForCaller
                    = conn.prepareCall("{CALL sp_caller_exist(?)}");
            
//            validateUser.registerOutParameter(userID, Types.INTEGER);
//            validateUser.registerOutParameter(password, Types.VARCHAR);
//            ResultSet resultSet = validateUser.executeQuery();
//            resultSet.next();
//            if (resultSet.getInt("") == 1) {
//                result = true;
//            }

        } catch (SQLException ex) {

            throw ex;
        }
        if (!exist) {
            try (Connection conn = Connector.createDBConnection()){
                CallableStatement createCaller
                    = conn.prepareCall("{CALL sp_create_caller(?)}");
                
                
            } catch (Exception e) {
            }
        } // end if !exist
        
        try (Connection conn = Connector.createDBConnection()){
            CallableStatement createCallRecord = conn.prepareCall("{Call sp_create_callrecord()}");
        } catch (Exception e) {
        }
        
        
        return result;
    }
    
    
} // end of class
