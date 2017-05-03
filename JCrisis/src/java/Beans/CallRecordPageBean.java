/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Accessors.CallRecordAccessor;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Laura Simmonds
 */
public class CallRecordPageBean {
    
    private ArrayList<CallRecord> callList;
    private String errorMessage = "Error";

    public CallRecordPageBean(ArrayList<CallRecord> callList) {
        this.callList = callList;
    }

    public CallRecordPageBean() {
    System.err.println("Constructor CallRecordPageBean");
    }
    
    public ArrayList<CallRecord> getCallList() {
        System.err.println("GetCallList");
        //if (null != callList) {
            try {
                this.callList = CallRecordAccessor.getCallList();
                System.err.println("got arrayList");
            } catch (Exception e) {
                this.callList = new ArrayList<>();
                this.errorMessage = e.getMessage();
                System.err.println(e.getMessage());
            }
        //}
        return callList;
    }

    /**
     * @param callList the callList to set
     */
    public void setCallList(ArrayList<CallRecord> callList) {
        this.callList = callList;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        System.err.println("GetErrorMessage");
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    
}
