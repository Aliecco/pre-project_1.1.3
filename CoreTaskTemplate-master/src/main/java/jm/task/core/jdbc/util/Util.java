package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final static String hostName = "localhost";
    private final static String dbName = "testdb";
    private final static String userName = "root";
    private final static String password = "alisher123";

    public static Connection getMySQLConnection() {
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
    public static boolean closeConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

            DriverManager.getConnection(connectionURL, userName,
                    password).close();
            return true;
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Ошибка при закрытии соединения");
        }
        return false;
    }
}
