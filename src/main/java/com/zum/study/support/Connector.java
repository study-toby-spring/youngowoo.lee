package com.zum.study.support;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Joeylee on 2017-09-01.
 */
public interface Connector {

    Connection createConnection() throws ClassNotFoundException, SQLException;
}
