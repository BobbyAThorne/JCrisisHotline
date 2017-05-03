/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Accessors.ResourceAccessor;
import java.util.ArrayList;

/**
 *
 * @author Laura Simmonds
 */
public class ResourcePagesBean {
    
    private ArrayList<Resource> resourceList;
    private String errorMessage = "Error";

    public ResourcePagesBean(ArrayList<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    public ResourcePagesBean() {
    System.err.println("Constructor ResourcePagesBean");
    }
    
    public ArrayList<Resource> getResourceList() {
        System.err.println("GetResourceList");
        //if (null != resourceList) {
            try {
                this.resourceList = ResourceAccessor.getResourceList();
                System.err.println("got arrayList");
            } catch (Exception e) {
                this.resourceList = new ArrayList<>();
                this.errorMessage = e.getMessage();
                System.err.println(e.getMessage());
            }
        //}
        return resourceList;
    }

    /**
     * @param resourceList the resourceList to set
     */
    public void setResourceList(ArrayList<Resource> resourceList) {
        this.resourceList = resourceList;
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
