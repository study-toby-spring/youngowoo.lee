package com.zum.study.program;

import com.zum.study.domain.User;
import com.zum.study.factory.CountingDaoFactory;
import com.zum.study.repository.UserDao;
import com.zum.study.support.impl.CountingUniversalConnector;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

/**
 * Created by Joeylee on 2017-09-01.
 */
public class Program {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CountingDaoFactory.class);

        CountingUniversalConnector connector = applicationContext.getBean("connector", CountingUniversalConnector.class);
        UserDao dao = applicationContext.getBean("userDao", UserDao.class);


        User user = new User();

        user.setId("hello8");
        user.setName("young woo lee");
        user.setPassword("password");

        dao.add(user);

        User found = dao.get("hello8");

        System.out.println("name : " + found.getName());
        System.out.println("password : " + found.getPassword());

        System.out.printf("counter : %d", connector.getCounter());
    }
}
