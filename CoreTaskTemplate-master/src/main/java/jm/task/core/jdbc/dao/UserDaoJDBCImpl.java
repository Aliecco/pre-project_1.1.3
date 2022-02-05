package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final String table = "new_table";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection conn = Util.getMySQLConnection(); Statement stm = conn.createStatement()) {
            String str = "CREATE TABLE `testdb`.`" + table + "` ( " +
                    " `id` INT NOT NULL AUTO_INCREMENT, " +
                    " `name` VARCHAR(45) NULL, " +
                    " `lastName` VARCHAR(45) NULL, " +
                    " `age` INT(3) NULL, " +
                    " PRIMARY KEY (`id`));";
            stm.executeUpdate(str);
            System.out.println("Таблица создана");

        } catch (SQLException | ClassNotFoundException ignore) {

        }
    }

    public void dropUsersTable() {
        try (Connection conn = Util.getMySQLConnection(); Statement stm = conn.createStatement()) {
            String str = "DROP TABLE " + table + ";";
            stm.executeUpdate(str);
            System.out.println("Таблица удалена");

        } catch (SQLException | ClassNotFoundException ignore) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection conn = Util.getMySQLConnection(); Statement stm = conn.createStatement()) {
            String str = "insert into " + table + "(name, lastName, age) " +
                    "values('"+name+"','"+lastName+"', "+age+");";
            stm.executeUpdate(str);
            System.out.println("User с именем " + name + "добавлен");

        } catch (SQLException | ClassNotFoundException ignore) {

        }
    }

    public void removeUserById(long id) {
        try (Connection conn = Util.getMySQLConnection(); Statement stm = conn.createStatement()) {
            String str = "DELETE FROM " + table + " WHERE id = " + id + ";";
            stm.executeUpdate(str);
            System.out.println("User удален");

        } catch (SQLException | ClassNotFoundException ignore) {

        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection conn = Util.getMySQLConnection(); Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery("select id, name, lastName, age from " + table + ";");
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
        try (Connection conn = Util.getMySQLConnection(); Statement stm = conn.createStatement()) {
            String str = "DELETE FROM " + table + ";";
            stm.executeUpdate(str);
            System.out.println("Таблица очищена");

        } catch (SQLException | ClassNotFoundException ignore) {

        }
    }
}
