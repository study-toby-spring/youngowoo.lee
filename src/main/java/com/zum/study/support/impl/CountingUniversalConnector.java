package com.zum.study.support.impl;

import com.zum.study.support.Connector;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Joeylee on 2017-09-02.
 */
@Configuration
public class CountingUniversalConnector implements Connector {

    private int counter = 0;
    public int getCounter() { return this.counter; }

    private Connector connector;

    public CountingUniversalConnector(Connector connector) {
        this.connector = connector;
    }

    public Connection createConnection() throws ClassNotFoundException, SQLException {

        System.out.println("Counting !!");
        this.counter++;

        return connector.createConnection();
    }
}
