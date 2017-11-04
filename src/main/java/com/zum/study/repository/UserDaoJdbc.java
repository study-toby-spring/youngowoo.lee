package com.zum.study.repository;

import com.zum.study.domain.User;
import com.zum.study.type.Level;
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
 * Created by Joeylee on 2017-09-13.
 */
public class UserDaoJdbc implements UserDao {


    private String sqlAdd;

    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> mapper = new RowMapper<User>() {

        public User mapRow(ResultSet result, int row) throws SQLException {

            User user = new User();

            user.setId(result.getString("id"));
            user.setName(result.getString("name"));
            user.setPassword(result.getString("password"));
            user.setEmail(result.getString("email"));
            user.setLevel(Level.valueOf(result.getInt("level")));
            user.setLogin(result.getInt("login"));
            user.setRecommend(result.getInt("recommend"));

            return user;
        }
    };

    public void setDataSource(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setSqlAdd(String sqlAdd) {

        this.sqlAdd = sqlAdd;
    }


    public void add(final User user) {

        jdbcTemplate.update(this.sqlAdd, user.getId(), user.getName(), user.getPassword(), user.getEmail(), user.getLevel().intValue(), user.getLogin(), user.getRecommend());

    }

    public List<User> getAll() {

        return jdbcTemplate.query("select * from users order by id", mapper);
    }

    public User get(String id) {

        return jdbcTemplate.queryForObject("select * from users where id = ?", new Object[] { id }, mapper);
    }

    public int getCount() {

        return jdbcTemplate.query(
                new PreparedStatementCreator() {

                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        return connection.prepareStatement("select count(*) from users");
                    }
                },
                new ResultSetExtractor<Integer>() {

                    public Integer extractData(ResultSet result) throws SQLException, DataAccessException {

                        result.next();
                        return result.getInt(1);
                    }
                });
    }

    public void deleteAll() {

        jdbcTemplate.update("delete from users");
    }

    public void update(User user) {

        jdbcTemplate.update("update users set name = ?, password = ?, email = ?, level = ?, login = ?, recommend = ? where id = ?", user.getName(), user.getPassword(), user.getEmail(), user.getLevel().intValue(), user.getLogin(), user.getRecommend(), user.getId());
    }
}


