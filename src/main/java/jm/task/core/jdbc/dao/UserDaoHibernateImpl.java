package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                     "id SERIAL PRIMARY KEY," +
                     "firstname VARCHAR(50)," +
                     "lastname VARCHAR(50)," +
                     "age smallint)";

        try (Session session = Util.getSessionFactory().openSession()) {

            session.beginTransaction();

            session.createSQLQuery(sql).executeUpdate();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {

            session.beginTransaction();

            session.createSQLQuery("DROP TABLE users").executeUpdate();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {

            session.beginTransaction();

            User user = new User(name,lastName,age);
            session.save(user);

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {

            session.beginTransaction();

            User user = session.get(User.class, id);
            session.delete(user);

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try ( Session session = Util.getSessionFactory().openSession()) {

            session.beginTransaction();

            userList = session.createQuery("FROM User ").getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (Session session = Util.getSessionFactory().openSession()) {

            session.beginTransaction();

            session.createSQLQuery(sql).executeUpdate();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
