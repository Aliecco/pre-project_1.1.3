package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getMySQLConnection() throws SQLException,
            ClassNotFoundException {
        final String hostName = "localhost";
        final String dbName = "testdb";
        final String userName = "root";
        final String password = "alisher123";

        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

            return DriverManager.getConnection(connectionURL, userName,
                    password);
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Ошибка при подключении к БД");
        }
        return null;
    }
}
