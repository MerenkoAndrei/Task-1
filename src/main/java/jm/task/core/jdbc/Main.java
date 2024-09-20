package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl u = new UserServiceImpl();
        //u.saveUser("Andrei","Merenko", (byte) 32);
        System.out.println(u.getAllUsers());
       //u.createUsersTable();
    }
}
