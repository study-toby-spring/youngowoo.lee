package com.zum.study.service.sql.registry;

import com.zum.study.exception.SqlUpdateFailureException;

import java.util.Map;

public interface UpdatableSqlRegistry extends SqlRegistry {
    void update(String key, String sql) throws SqlUpdateFailureException;
    void update(Map<String, String> sqlMap) throws SqlUpdateFailureException;
}