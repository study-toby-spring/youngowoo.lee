package com.zum.study.repository;

import com.zum.study.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Joeylee on 2017-09-01.
 */
public interface UserDao {

    void add(final User user);

    List<User> getAll();
    User get(String id);

    int getCount();
    void deleteAll();

    void update(User user);
}