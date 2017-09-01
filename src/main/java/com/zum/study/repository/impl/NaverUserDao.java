package com.zum.study.repository.impl;

import com.zum.study.repository.UserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Joeylee on 2017-09-01.
 */
public class NaverUserDao extends UserDao{

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        System.out.println("naver");


        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/spring", "root", "root");
    }
}
