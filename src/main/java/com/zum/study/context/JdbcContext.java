package com.zum.study.context;

import com.zum.study.strategy.Statement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Joeylee on 2017-09-07.
 */
public class JdbcContext {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void workWithStatement(Statement statement) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = dataSource.getConnection();

            preparedStatement = statement.getPreparedStatement(connection);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw e;
        }
        finally {

            if (preparedStatement != null) {

                try {
                    preparedStatement.close();
                }
                catch (SQLException e) { }
            }

            if (connection != null) {

                try {
                    connection.close();
                }
                catch (SQLException e) { }
            }
        }
    }
}
