/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accessors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DragonSheep
 */
public class Connector {
    /**
     * Creates Connection to JCrisis Hotline Database
     * @return DriverManager.getConnection(connectionString);
     * @throws SQLException 
     */
    public static Connection createDBConnection() throws SQLException {
        String databaseUrl = "localhost";
        String port = "3306";
        String databaseName = "JCrisis_Hotline_DB";
        String databaseUserName = "JCrisisServer";
        String password = "apple";
        String connectionString = "jdbc:mysql://" + databaseUrl + ":" 
                + port
                + "/" + databaseName 
                + "?useSSL=false"
                +"&user=" + databaseUserName 
                + "&password="+ password 
                + "&noAccessToProcedureBodies=true";
        DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
        return DriverManager.getConnection(connectionString);
    }
}
