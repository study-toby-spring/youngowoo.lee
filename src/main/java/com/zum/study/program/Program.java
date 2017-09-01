package com.zum.study.program;

import com.zum.study.domain.User;
import com.zum.study.repository.UserDao;
import com.zum.study.support.Connector;
import com.zum.study.support.impl.NaverConnector;

import java.sql.SQLException;

/**
 * Created by Joeylee on 2017-09-01.
 */
public class Program {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Connector connector = new NaverConnector();
        UserDao dao = new UserDao(connector);

        User user = new User();

        user.setId("hello4");
        user.setName("young woo lee");
        user.setPassword("password");

        dao.add(user);

        User found = dao.get("hello4");

        System.out.println("name : " + found.getName());
        System.out.println("password : " + found.getPassword());
    }
}
