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

}
