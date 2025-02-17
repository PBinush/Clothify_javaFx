package edu.icet.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance ;

    private Connection connection;

    private DBConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clothifystore","root","12345");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Connection getConnection(){
        return connection;
    }
    public static DBConnection getInstance(){
        return instance==null? instance = new DBConnection():instance;
    }

}
