package Accessors;

import Beans.Resource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

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
     * Creates a Resource Category.
     *
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
     * Creates a Resource Category of Resources.
     *
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
