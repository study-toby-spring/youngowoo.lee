package com.zum.study.program;

import com.zum.study.domain.User;
import com.zum.study.repository.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

/**
 * Created by Joeylee on 2017-09-01.
 */
public class Program {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/context/application-context.xml");
        UserDao dao = applicationContext.getBean("userDao", UserDao.class);


        User user = new User();

        user.setId("hello15");
        user.setName("young woo lee");
        user.setPassword("password");

        dao.add(user);

        User found = dao.get("hello15");

        System.out.println("name : " + found.getName());
        System.out.println("password : " + found.getPassword());

    }
}
