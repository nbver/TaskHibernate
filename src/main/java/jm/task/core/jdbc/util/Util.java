package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getMySQLConnection()
            throws  SQLException {
        String hostName = "localhost";
        String dbName = "my_first_db";
        String userName = "timur_aka";
        String password = "cdfvbg";
        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password)
            throws SQLException {

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?serverTimezone=Europe/Moscow";

        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        return conn;
    }
}
