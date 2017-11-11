package com.zum.study.service.sql.service;

import com.zum.study.exception.SqlRetrievalFailureException;

public interface SqlService {

    String getSql(String key) throws SqlRetrievalFailureException;
}
