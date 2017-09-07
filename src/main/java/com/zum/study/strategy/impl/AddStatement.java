package com.zum.study.strategy.impl;

import com.zum.study.domain.User;
import com.zum.study.strategy.base.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Joeylee on 2017-09-07.
 */

public class AddStatement implements Statement {

    private User user;

    public AddStatement(User user) {

        this.user = user;
    }

    public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("insert into users (id, name, password) values (?, ?, ?)");

        preparedStatement.setString(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getPassword());

        return preparedStatement;
    }
}

