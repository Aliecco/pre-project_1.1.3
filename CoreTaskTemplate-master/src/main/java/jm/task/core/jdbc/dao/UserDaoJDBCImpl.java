package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final String table = "new_table";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection conn = Util.getMySQLConnection()) {
            conn.setAutoCommit(false);
            String str = "CREATE TABLE `testdb`.`"+table+"` ( " +
                    " `id` INT NOT NULL AUTO_INCREMENT, " +
                    " `name` VARCHAR(45) NULL, " +
                    " `lastName` VARCHAR(45) NULL, " +
                    " `age` INT(3) NULL, " +
                    " PRIMARY KEY (`id`));";
            PreparedStatement stm = conn.prepareStatement(str);
            //stm.setString(1, table);
            stm.executeUpdate();
            conn.commit();

            System.out.println("Таблица создана");

        } catch (SQLException | ClassNotFoundException ignore) {

        }
    }

    public void dropUsersTable() {
        try (Connection conn = Util.getMySQLConnection()) {
            conn.setAutoCommit(false);
            String str = "DROP TABLE " + table + ";";
            PreparedStatement stm = conn.prepareStatement(str);
            //stm.setString(1, table);
            stm.executeUpdate();
            conn.commit();

            System.out.println("Таблица удалена");

        } catch (SQLException | ClassNotFoundException ignore) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection conn = Util.getMySQLConnection()) {
            conn.setAutoCommit(false);
            String str = "insert into " + table + " (name, lastName, age) " +
                    "values('" + name + "','" + lastName + "', " + age + ");";
            PreparedStatement stm = conn.prepareStatement(str);
            //stm.setString(1, table);
            stm.executeUpdate();
            conn.commit();

            System.out.println("User с именем " + name + " добавлен");

        } catch (SQLException | ClassNotFoundException ignore) {

        }
    }

    public void removeUserById(long id) {
        try (Connection conn = Util.getMySQLConnection()) {
            conn.setAutoCommit(false);
            String str = "DELETE FROM " + table + " WHERE id = ?;";
            PreparedStatement stm = conn.prepareStatement(str);
            stm.setLong(1, id);
            stm.executeUpdate();
            conn.commit();

            System.out.println("User удален");

        } catch (SQLException | ClassNotFoundException ignore) {

        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection conn = Util.getMySQLConnection()) {
            conn.setAutoCommit(false);
            String str = "select id, name, lastName, age from " + table +";";
            PreparedStatement stm = conn.prepareStatement(str);
            //stm.setString(1, table);
            ResultSet rs = stm.executeQuery();
            conn.commit();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                list.add(user);
            }

        } catch (SQLException | ClassNotFoundException ignore) {

        }
        return list;
    }

    public void cleanUsersTable() {
        try (Connection conn = Util.getMySQLConnection()) {
            conn.setAutoCommit(false);
            String str = "DELETE FROM " + table + ";";
            PreparedStatement stm = conn.prepareStatement(str);
            //stm.setString(1, table);
            stm.executeUpdate();
            conn.commit();

            System.out.println("Таблица очищена");

        } catch (SQLException | ClassNotFoundException ignore) {

        }
    }
}
