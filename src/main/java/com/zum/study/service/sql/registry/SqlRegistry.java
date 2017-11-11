package com.zum.study.service.sql.registry;

import com.zum.study.exception.SqlRetrievalFailureException;

public interface SqlRegistry {

    void register(String key, String sql);
    String get(String key) throws SqlRetrievalFailureException;
}
