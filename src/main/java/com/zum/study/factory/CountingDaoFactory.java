package com.zum.study.factory;

import com.zum.study.repository.UserDao;
import com.zum.study.support.Connector;
import com.zum.study.support.impl.CountingUniversalConnector;
import com.zum.study.support.impl.NaverConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * Created by Joeylee on 2017-09-02.
 */
@Configuration
public class CountingDaoFactory {

    @Bean
    public Connector connector() throws SQLException, ClassNotFoundException {

        return new CountingUniversalConnector(realConnector());
    }

    @Bean
    public Connector realConnector() throws SQLException, ClassNotFoundException {

        return new NaverConnector();
    }

    @Bean("userDao")
    public UserDao userDao() throws SQLException, ClassNotFoundException {

        return new UserDao(connector());
    }
}
