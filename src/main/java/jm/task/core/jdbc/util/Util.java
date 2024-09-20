package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Util {
    public static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (connection != null) {
                System.out.println("Connection is successful");
            } else {
                System.out.println("Connection failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory;
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }
}
