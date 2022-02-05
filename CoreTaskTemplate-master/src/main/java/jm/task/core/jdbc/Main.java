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
        usr.saveUser("ASdbjc", "ascx", (byte) 45);
        usr.saveUser("hbnk", "dgc", (byte) 4);
        usr.saveUser("yghbn", "ihj,", (byte) 23);
        usr.saveUser("rdgf", "wsf", (byte) 38);
        List<User> list = usr.getAllUsers();

        for (int i = 0; i < list.size(); i++) {
            StringBuilder table = new StringBuilder();
            table.append(list.get(i).getId().toString()).append(" ").append(list.get(i).getName().toString())
                    .append(" ").append(list.get(i).getLastName().toString()).append(" ")
                    .append(list.get(i).getAge().toString()).append(" ");
            System.out.println(table);
        }
        //usr.removeUserById(4);
        usr.cleanUsersTable();
        usr.dropUsersTable();
    }
}
