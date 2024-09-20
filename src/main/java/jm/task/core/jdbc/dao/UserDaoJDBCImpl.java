package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
//            connection.setAutoCommit(false);
            String sql = "CREATE TABLE users (" +
                         "id SERIAL PRIMARY KEY," +
                         "firstname VARCHAR(50)," +
                         "lastname VARCHAR(50)," +
                         "age INTEGER)";

            statement.execute(sql);
//            confirmation(connection);
            System.out.println("Операция выполнена!");

        } catch (SQLException e) {
            System.err.println("Что - то пошло не так........" + e.getStackTrace());
        }
    }


    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
//            connection.setAutoCommit(false);
            String sql = "DROP TABLE users";

            statement.execute(sql);
//            confirmation(connection);
            System.out.println("Операция выполнена!");

        } catch (SQLException e) {
            System.err.println("Что - то пошло не так........" + e.getStackTrace());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection()) {
//            connection.setAutoCommit(false);
            String sql = "INSERT INTO users(firstname, lastname, age) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();

//            confirmation(connection);
            preparedStatement.close();
            System.out.println("Операция выполнена!");

        } catch (SQLException e) {
            System.err.println("Что - то пошло не так........" + e.getStackTrace());
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection()) {
//            connection.setAutoCommit(false);
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

//            confirmation(connection);
            preparedStatement.close();
            System.out.println("Операция выполнена!");

        } catch (SQLException e) {
            System.err.println("Что - то пошло не так........" + e.getStackTrace());
        }

    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            String sql = "SELECT id, firstname, lastname, age FROM users";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User(resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        (byte) resultSet.getInt("age"));
                usersList.add(user);
            }
            resultSet.close();

        } catch (SQLException e) {
            System.err.println("Что - то пошло не так........" + e.getStackTrace());
        }
        return usersList;
    }


    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
//            connection.setAutoCommit(false);
            String sql = "TRUNCATE TABLE users";
            statement.executeUpdate(sql);

//            confirmation(connection);
            statement.close();
            System.out.println("Операция выполнена!");

        } catch (SQLException e) {
            System.err.println("Что - то пошло не так........" + e.getStackTrace());
        }
    }

//    private void confirmation(Connection connection) {
//        try (Scanner scanner = new Scanner(System.in)) {
//            System.out.println("Для сохранения изменений подтвердите ведя значение!Введите y(yes)/n(no)?");
//            String sc = scanner.nextLine();
//            if (sc.equalsIgnoreCase("y")) {
//                connection.commit();
//            } else if (sc.equalsIgnoreCase("n")) {
//                connection.rollback();
//            }
//        } catch (SQLException e) {
//            System.err.println("Что - то пошло не так........" + e.getStackTrace());
//        }
//    }
}
