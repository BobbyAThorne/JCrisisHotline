package Accessors;

import Beans.CallRecord;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Laura Simmonds
 */
public class CallRecordAccessor {

    /**
     * Laura Simmonds Retrieves a list of call records
     *
     * @return userList
     * @throws SQLException
     */
    public static ArrayList<CallRecord> getCallList() throws SQLException {
        ArrayList<CallRecord> callList = null;
        //java.sql.Time recordTime;
        //java.sql.Date recordDate;
        //LocalDateTime localDateTime;

        try (Connection conn = Connector.createDBConnection()) {
            callList = new ArrayList<>();
            CallableStatement sp_retrieve_callrecord_list
                    = conn.prepareCall("Call sp_retrieve_callrecord_list()");
            ResultSet resultSet = sp_retrieve_callrecord_list.executeQuery();
            while (resultSet.next()) {
                //recordDate = resultSet.getDate("End_Time");
                //recordTime = resultSet.getTime("End_Time");
                //localDateTime = LocalDateTime.parse(recordDate + "T" + recordTime);
                callList.add(new CallRecord(
                        resultSet.getString("End_Time"),
                        resultSet.getString("Call_Type_ID"),
                        resultSet.getString("Call_Description")
                ));
            }
        } catch (SQLException ex) {
            throw ex;
        }
        
        return callList;
    }
}
