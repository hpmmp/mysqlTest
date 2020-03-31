package com.cqut.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static ConnectionManager instance;
    private String driver;
    private String url;
    private String userName;
    private String password;


    protected ConnectionManager() throws Exception {
        driver = PropertyManager.getProperty("jdbc.driver");
        url = PropertyManager.getProperty("jdbc.url");
        userName = PropertyManager.getProperty("jdbc.username");
        password = PropertyManager.getProperty("jdbc.password");
        /*driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/connect_test";
        userName = "root";
        password = "123456";*/

        Class.forName(driver);
    }

    public static synchronized ConnectionManager getInstance() throws
            Exception {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() throws Exception {
        try {
            return DriverManager.getConnection
                    (url, userName, password);
        } catch (SQLException ex) {

            throw new Exception(ex);
        } catch (Exception ex) {
            throw new Exception(ex);
        }

    }

}