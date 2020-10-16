package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection;

    {
        try {
            connection = Util.getMySQLConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public UserDaoJDBCImpl() {

    }


    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS `my_first_db`.`users` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastname` VARCHAR(45) NOT NULL,\n" +
                    "  `age` TINYINT(3) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS `users`;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String SAVE_USER = "INSERT INTO `my_first_db`.`users` (`name`, `lastname`, `age`) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String REMOVE_USER = "DELETE FROM `my_first_db`.`users` WHERE (`id` = ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `users`;");
            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String lastname = resultSet.getString(3);
                Byte age = resultSet.getByte(4);
                users.add(new User(name, lastname, age));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE TABLE `my_first_db`.`users`;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
