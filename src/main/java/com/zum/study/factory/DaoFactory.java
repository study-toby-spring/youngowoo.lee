package com.zum.study.factory;

import com.zum.study.repository.UserDao;
import com.zum.study.support.Connector;
import com.zum.study.support.impl.NaverConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Joeylee on 2017-09-02.
 */
@Configuration
public class DaoFactory {

    @Bean("userDao")
    public UserDao userDao() {

        Connector connector = new NaverConnector();
        return new UserDao(connector);
    }
}
