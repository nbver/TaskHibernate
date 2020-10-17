package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Nikita", "Brat", (byte) 20);
        userService.saveUser("Anya", "Sestra", (byte) 127);
        userService.saveUser("Ashot", "Petrosyan", (byte) 60);
        userService.saveUser("Sasha", "Sushkina", (byte) 100);
        userService.cleanUsersTable();
        List<User> users = userService.getAllUsers();

        for (User user : users) {
            System.out.println(user);
        }

        userService.dropUsersTable();

        Util.getSessionFactory().close();
    }
}
