package Accessors;

import Beans.Resource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 * Resource Data Accessors.
 *
 * @author Christian Lopez
 *
 * Updated: 2017/04/18 By: Alissa Duffy Standardized Commenting.
 */
public class ResourceAccessor {

    /**
     * Christian Lopez Creates a Resource
     *
     * @param newResource
     * @return
     * @throws SQLException
     */
    public static boolean createResource(Resource newResource) throws SQLException {

        boolean success = false;

        try (Connection conn = Connector.createDBConnection()) {
            CallableStatement createResource
                    = conn.prepareCall("{CALL sp_create_resource(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            createResource.setString(1, newResource.getName());
            createResource.setString(2, newResource.getPhone());
            createResource.setString(3, newResource.getAddressOne());
            createResource.setString(4, newResource.getAddressTwo());
            createResource.setString(5, newResource.getCity());
            createResource.setString(6, newResource.getTerritory());
            createResource.setString(7, newResource.getCountry());
            createResource.setString(8, newResource.getPostalCode());
            createResource.setString(9, newResource.getEmail());
            createResource.setString(10, newResource.getDescription());

            ResultSet resultSet = createResource.executeQuery();
            if (resultSet.next()) {
                newResource.setResourceId(resultSet.getInt("new_id"));
                success = true;
            }

        } catch (SQLException ex) {
            throw ex;
        }

        return success;
    }

    /**
     * Jessica Hoppe ArrayList of Resource Category Details
     *
     * @ return
     * @ throws SQLException
     */
    public static ArrayList<Resource> retreveResourceList() throws SQLException {
        ArrayList<Resource> resourceList = new ArrayList<Resource>();

        try (Connection conn = Connector.createDBConnection()) {
            CallableStatement retrieveResource
                    = conn.prepareCall("{CALL sp_retrive_resource_list()}");

            ResultSet resultSet = retrieveResource.executeQuery();
            while (resultSet.next()) {
                resourceList.add(new Resource(               
                    resultSet.getInt("Resource_ID"),
                        null,
                    resultSet.getString("Name"),
                    resultSet.getString("Phone"),
                    resultSet.getString("Address_One"),
                    resultSet.getString("Address_Two"),
                    resultSet.getString("City"),
                    resultSet.getString("Territory"),
                    resultSet.getString("Country"),
                    resultSet.getString("Postal_Code"),
                    resultSet.getString("Email"),
                    resultSet.getString("Description")
                ));
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return resourceList;
    }

    /**
     * Christian Lopez
     *
     * @param categoryName
     * @return
     * @throws SQLException
     */
    public static int getOccurances(String categoryName) throws SQLException {
        int count = 0;

        try (Connection conn = Connector.createDBConnection()) {
            CallableStatement retrieveCount
                    = conn.prepareCall("{CALL sp_retrieve_count_of_given_resource_category(?)}");
            retrieveCount.setString(1, categoryName);

            ResultSet resultSet = retrieveCount.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("Num_Occurances");
            }

        } catch (SQLException ex) {
            throw ex;
        }

        return count;
    }

    /**
     * Jessica Hoppe
     * Creates a Resource Category.
     * Creates a connection to the database call to execute query
     * @param categoryId
     * @param categoryDesc
     * @return
     * @throws SQLException
     */
    public static boolean createResourceCategory(String categoryId, String categoryDesc) throws SQLException {
        boolean success = false;

        try (Connection conn = Connector.createDBConnection()) {
            CallableStatement createResourceCategory
                    = conn.prepareCall("{CALL sp_create_resource_category(?, ?)}");
            createResourceCategory.setString(1, categoryId);
            createResourceCategory.setString(2, categoryDesc);

            ResultSet resultSet = createResourceCategory.executeQuery();

            success = true;

        } catch (SQLException ex) {
            throw ex;
        }

        return success;
    }

    /**
     * Jessica Hoppe
     * Creates a Resource Category of Resources.
     * Creates connection to the database call to execute query
     * @param categoryId
     * @param resourceId
     * @return
     * @throws SQLException
     */
    public static boolean createResourceCategoryResource(String categoryId, int resourceId) throws SQLException {
        boolean success = false;

        try (Connection conn = Connector.createDBConnection()) {
            CallableStatement createResourceCategoryResource
                    = conn.prepareCall("{CALL sp_create_resource_category_resource(?, ?)}");
            createResourceCategoryResource.setString(1, categoryId);
            createResourceCategoryResource.setInt(2, resourceId);

            ResultSet resultSet = createResourceCategoryResource.executeQuery();

            success = true;

        } catch (SQLException ex) {
            throw ex;
        }

        return success;
    }
    
    /**
     * Updates an existing Resource in the database.
     * Christian Lopez
     * 2017/04/25
     * @param oldResource
     * @param newResource
     * @return 
     * @throws java.sql.SQLException 
     */
    public static boolean updateResourceProvider(Resource oldResource, Resource newResource) throws SQLException {
        boolean success = false;
        try (Connection conn = Connector.createDBConnection()){
            CallableStatement updateResourceProvider = 
                    conn.prepareCall("{CALL sp_update_resource_provider(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            updateResourceProvider.setInt(1, oldResource.getResourceId());
            
            updateResourceProvider.setString(2, oldResource.getName());
            updateResourceProvider.setString(3, oldResource.getPhone());
            updateResourceProvider.setString(4, oldResource.getAddressOne());
            updateResourceProvider.setString(5, oldResource.getAddressTwo());
            updateResourceProvider.setString(6, oldResource.getCity());
            updateResourceProvider.setString(7, oldResource.getTerritory());
            updateResourceProvider.setString(8, oldResource.getCountry());
            updateResourceProvider.setString(9, oldResource.getPostalCode());
            updateResourceProvider.setString(10, oldResource.getEmail());
            updateResourceProvider.setString(11, oldResource.getDescription());
            
            updateResourceProvider.setString(12, newResource.getName());
            updateResourceProvider.setString(13, newResource.getPhone());
            updateResourceProvider.setString(14, newResource.getAddressOne());
            updateResourceProvider.setString(15, newResource.getAddressTwo());
            updateResourceProvider.setString(16, newResource.getCity());
            updateResourceProvider.setString(17, newResource.getTerritory());
            updateResourceProvider.setString(18, newResource.getCountry());
            updateResourceProvider.setString(19, newResource.getPostalCode());
            updateResourceProvider.setString(20, newResource.getEmail());
            updateResourceProvider.setString(21, newResource.getDescription());
            
            ResultSet results = updateResourceProvider.executeQuery();
            
            success = true;
            conn.close();
            
        } catch (SQLException e) {
            throw e;
        }
        
        return success;
    }
    
    /**
     * Retrieves one resource by it's id.
     * Christian Lopez
     * 2017/04/25
     * @param resourceId
     * @return
     * @throws Exception 
     */
    public static Resource retrieveResourceById(int resourceId) throws Exception {
        Resource resource = null;
        
        try(Connection conn = Connector.createDBConnection()) {
            CallableStatement retrieveResource = 
                    conn.prepareCall("{CALL sp_retrieve_resource_provider_by_id(?)}");
            retrieveResource.setInt(1, resourceId);
            
            ResultSet result = retrieveResource.executeQuery();
            if(result.first()) {
                resource = new Resource(result.getInt(1),null, result.getString(2), result.getString(3),
                result.getString(4), result.getString(5), result.getString(6), result.getString(7),
                result.getString(8), result.getString(9), result.getString(10), result.getString(11));
            } else {
                throw new NullPointerException("Could not find requested resource.");
            }
            
             
            
//      (int resourceId, String categories, String name, String phone, String addressOne,
//              String addressTwo, String city, String territory, String country, String postalCode, String email, String description) {
                    
//                    
//                    SELECT Resource_ID,
//    Name,
//    Phone,
//    Address_One,
//    Address_Two,
//    City,
//    Territory,
//    Country,
//    Postal_Code,
//    Email,
//    Description
                    
        } catch (Exception e) {
            throw e;
        }
        
        return resource;
    }
    
    
    public static String retrieveCSVCategoriesByResourceId(int resourceId) throws Exception {
        String list = "";
        
        try (Connection conn = Connector.createDBConnection()){
            CallableStatement retrieveCategories = 
                    conn.prepareCall("{CALL sp_retrieve_resource_categories(?)}");
            retrieveCategories.setInt(1, resourceId);
            
            ResultSet result = retrieveCategories.executeQuery();
            while(result.next()) {
                list += result.getString(1)+", ";
            }
            
            list = list.substring(0, list.length() - 2);
            
        } catch (Exception e) {
            throw e;
        }
        
        
        return list;
    }
    
    /**
     * Laura Simmonds
     * Retrieves a list of Resources
     *
     * @return userList
     * @throws SQLException
     */
    public static ArrayList<Resource> getResourceList() throws SQLException {
        ArrayList<Resource> resourceList = null;
        try (Connection conn = Connector.createDBConnection()) {
            resourceList = new ArrayList<>();
            CallableStatement sp_retrieve_resource_list
                    = conn.prepareCall("Call sp_retrieve_resource_list()");
            ResultSet resultSet = sp_retrieve_resource_list.executeQuery();
            while (resultSet.next()) {
                resourceList.add(new Resource(
                        resultSet.getString("Name"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Description")
                ));
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return resourceList;
    }

}
