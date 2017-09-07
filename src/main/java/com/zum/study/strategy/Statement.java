package com.zum.study.strategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Joeylee on 2017-09-06.
 */

public interface Statement {

    PreparedStatement getPreparedStatement(Connection connection) throws SQLException;
}