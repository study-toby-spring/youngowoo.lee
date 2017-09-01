package com.zum.study.repository;

import com.zum.study.domain.User;
import com.zum.study.support.Connector;
import com.zum.study.support.impl.NaverConnector;

import java.sql.*;

/**
 * Created by Joeylee on 2017-09-01.
 */
public class UserDao {

    private Connector connector;

    public UserDao() {

        this.connector = new NaverConnector();
    }

    public void add(User user) throws ClassNotFoundException, SQLException {

        Connection connection = connector.createConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("insert into users (id, name, password) values (?, ?, ?)");

        preparedStatement.setString(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getPassword());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {

        Connection connection = connector.createConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id = ?");
        preparedStatement.setString(1, id);

        ResultSet result = preparedStatement.executeQuery();
        result.next();


        User user = new User();

        user.setId(result.getString("id"));
        user.setName(result.getString("name"));
        user.setPassword(result.getString("password"));

        result.close();
        preparedStatement.close();
        connection.close();

        return user;
    }
}

