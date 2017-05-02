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
 * @author Jessica Hoppe
 * Resource Category Bean to get the resourceList
 */
public class ResourcePagBean extends Resource{
    
    ArrayList<Resource> resourceList;
    
    public ArrayList<Resource> getResourceList() throws Exception {
        //return resourceList;
        ArrayList<Resource> resources = new ArrayList<Resource>();
        
        try {
            resources = ResourceAccessor.retreveResourceList();
        } catch (Exception e) {
            throw e;
        }
        
        return resources;
    }
    
    public void setResourceList(ArrayList<Resource> resourceList) {
        this.resourceList = resourceList;
    }
    
    public ResourcePagBean() {
        
    }
    
    public void setErrorMessage(String message) {
        super.setError(message);
    }
    
    public String getErrorMessage() {
        return super.getError();
    }
    
    
}
