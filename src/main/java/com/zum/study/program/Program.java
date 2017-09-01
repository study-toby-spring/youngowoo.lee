package com.zum.study.program;

import com.zum.study.domain.User;
import com.zum.study.repository.UserDao;

import java.sql.SQLException;

/**
 * Created by Joeylee on 2017-09-01.
 */
public class Program {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        UserDao dao = new UserDao();

        User user = new User();

        user.setId("hello");
        user.setName("young woo lee");
        user.setPassword("password");

        dao.add(user);

        User found = dao.get("hello");

        System.out.println("name : " + found.getName());
        System.out.println("password : " + found.getPassword());
    }
}
