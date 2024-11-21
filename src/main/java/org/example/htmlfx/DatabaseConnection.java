package org.example.htmlfx;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Driver;
public class DatabaseConnection {
    public static Connection databaseLink;

    public static Connection getConnection() {
        String databaseName = "library_management";
        String databaseUser = "root";
        String databasePassword = "423015";
        String databaseUrl = "jdbc:mysql://localhost:3306/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }
}