package com.zum.study.factory;

import com.zum.study.repository.UserDao;
import com.zum.study.support.Connector;
import com.zum.study.support.impl.NaverConnector;

/**
 * Created by Joeylee on 2017-09-02.
 */
public class DaoFactory {

    public UserDao userDao() {

        Connector connector = new NaverConnector();
        return new UserDao(connector);
    }
}
