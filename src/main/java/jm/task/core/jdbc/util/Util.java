package jm.task.core.jdbc.util;

//model
import jm.task.core.jdbc.model.User;

//jdbc
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//hibernate
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Util {

    private Util(){}

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties prop= new Properties();
                //provide the required properties
                prop.setProperty("hibernate.connection.url",  "jdbc:mysql://localhost:3306/my_first_db?serverTimezone=Europe/Moscow");
                prop.setProperty("hibernate.connection.username", "timur_aka");
                prop.setProperty("hibernate.connection.password", "cdfvbg");
                prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
                prop.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
                prop.setProperty("hibernate.connection.autocommit", "true");
                //create a configuration
                Configuration config = new Configuration();
                //provide all properties to this configuration
                config.setProperties(prop);
                //add classes which are mapped to database tables.
                config.addAnnotatedClass(User.class);
                //initialize session factory
                sessionFactory = config.buildSessionFactory();

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }

    public static Connection getMySQLConnection()
            throws  SQLException {
        String hostName = "localhost";
        String dbName = "my_first_db";
        String userName = "timur_aka";
        String password = "cdfvbg";
        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(
            String hostName,
            String dbName,
            String userName,
            String password)
            throws SQLException {

        String connectionURL = "jdbc:mysql://" +
                hostName + ":3306/" +
                dbName +
                "?serverTimezone=Europe/Moscow";
        Connection conn = DriverManager.getConnection(
                connectionURL,
                userName,
                password);
        return conn;
    }
}
