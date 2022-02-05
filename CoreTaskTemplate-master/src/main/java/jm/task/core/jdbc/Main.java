package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl usr = new UserServiceImpl();
        usr.createUsersTable();
        usr.saveUser("Dale", "Cooper", (byte) 32);
        usr.saveUser("Laura", "Palmer", (byte) 17);
        usr.saveUser("Harry", "Truman", (byte) 35);
        usr.saveUser("Josie", "Packard", (byte) 30);
        List<User> list = usr.getAllUsers();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
        usr.cleanUsersTable();
        usr.dropUsersTable();
    }
}
