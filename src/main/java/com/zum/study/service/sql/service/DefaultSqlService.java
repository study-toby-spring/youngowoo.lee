package com.zum.study.service.sql.service;

import com.zum.study.service.sql.reader.JaxbXmlSqlReader;
import com.zum.study.service.sql.registry.HashMapSqlRegistry;

public class DefaultSqlService extends BaseSqlService {

    public DefaultSqlService() {

        setSqlRegistry(new HashMapSqlRegistry());
        setSqlReader(new JaxbXmlSqlReader());
    }
}
