package com.zum.study.service.sql.service;

import com.zum.study.exception.SqlRetrievalFailureException;
import com.zum.study.service.sql.reader.SqlReader;
import com.zum.study.service.sql.registry.SqlRegistry;

import javax.annotation.PostConstruct;

public class BaseSqlService implements SqlService {

    private SqlRegistry registry;

    public void setSqlRegistry(SqlRegistry registry) {

        this.registry = registry;
    }

    private SqlReader reader;

    public void setSqlReader(SqlReader reader) {

        this.reader = reader;
    }


    @PostConstruct
    public void initialize() {

        reader.load(registry);
    }

    public String getSql(String key) throws SqlRetrievalFailureException {

        String sql = registry.get(key);

        if (sql == null) {
            throw new SqlRetrievalFailureException(key + "에 대한 SQL을 찾을 수 없습니다");
        }

        return sql;
    }
}