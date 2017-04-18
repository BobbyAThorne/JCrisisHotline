package Accessors;

import Beans.Resource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author Christian Lopez
 */
public class ResourceAccessor {
    
    public boolean createResource(Resource newResource) throws SQLException {
        boolean success = false;
        
        try(Connection conn = Connector.createDBConnection()){
            CallableStatement createResource = 
                  conn.prepareCall("{CALL sp_create_resource(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
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
            
        } catch(SQLException ex) {
            throw ex;
        }
        
        return success;
    }
    
}
