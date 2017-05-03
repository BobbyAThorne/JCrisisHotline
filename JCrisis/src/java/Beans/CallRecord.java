/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.time.LocalDateTime;

/**
 *
 * @author Eric Walton
 */
public class CallRecord {
 
    private int callRecordID;
    private String startTime;
    private int counselorID;
    private String callDescription;
    private String callTypeID;
    private int callerID;
    private String endTime;

    public CallRecord() {
    }

    public CallRecord(int callRecordID, String startTime, int counselorID, String callDescription, String callTypeID, int callerID, String endTime) {
        this.callRecordID = callRecordID;
        this.startTime = startTime;
        this.counselorID = counselorID;
        this.callDescription = callDescription;
        this.callTypeID = callTypeID;
        this.callerID = callerID;
        this.endTime = endTime;
    }
    
      public CallRecord(String endTime, String callTypeID, String callDescription) {
        this.endTime = endTime;
        this.callTypeID = callTypeID;
        this.callDescription = callDescription;
    }
    
    /**
     * @return the callRecordID
     */
    public int getCallRecordID() {
        return callRecordID;
    }

    /**
     * @param callRecordID the callRecordID to set
     */
    public void setCallRecordID(int callRecordID) {
        this.callRecordID = callRecordID;
    }

    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the counselorID
     */
    public int getCounselorID() {
        return counselorID;
    }

    /**
     * @param counselorID the counselorID to set
     */
    public void setCounselorID(int counselorID) {
        this.counselorID = counselorID;
    }

    /**
     * @return the callDescription
     */
    public String getCallDescription() {
        return callDescription;
    }

    /**
     * @param callDescription the callDescription to set
     */
    public void setCallDescription(String callDescription) {
        this.callDescription = callDescription;
    }

    /**
     * @return the callTypeID
     */
    public String getCallTypeID() {
        return callTypeID;
    }

    /**
     * @param callTypeID the callTypeID to set
     */
    public void setCallTypeID(String callTypeID) {
        this.callTypeID = callTypeID;
    }

    /**
     * @return the callerID
     */
    public int getCallerID() {
        return callerID;
    }

    /**
     * @param callerID the callerID to set
     */
    public void setCallerID(int callerID) {
        this.callerID = callerID;
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
    
    
} // end of class
