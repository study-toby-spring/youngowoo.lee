package com.zum.study.service.sql;

import com.zum.study.exception.SqlRetrievalFailureException;

/**
 * Created by Joeylee on 2017-11-05.
 */
public interface SqlService {

    String getSql(String key) throws SqlRetrievalFailureException;
}

