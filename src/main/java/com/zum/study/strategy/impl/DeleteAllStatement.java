package com.zum.study.strategy.impl;

import com.zum.study.strategy.base.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Joeylee on 2017-09-06.
 */
public class DeleteAllStatement implements Statement {

    public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {

        return connection.prepareStatement("delete * from users");
    }
}
